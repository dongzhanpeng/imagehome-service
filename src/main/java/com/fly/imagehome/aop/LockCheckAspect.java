package com.fly.imagehome.aop;

import com.alibaba.fastjson.JSON;
import com.fly.imagehome.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname LockCheckAspect
 * @Description aspect 增强
 * @Date 2021/12/9 15:19
 * @Author Fly
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class LockCheckAspect {

    @Resource
    private RedissonClient redissonClient;

    //定义切点，作用在使用了{@link LockCheck}注解的地方
    @Pointcut("@annotation(com.fly.imagehome.aop.annotation.LockCheck)")
    public void lockPointCut() {
        log.info("使用LockCheck注解控制切入点");
    }

    @Around("lockPointCut()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        //获取方法参数
        Object[] args = point.getArgs();
        //获取方法名
        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().getName();
        //记录参数
        log.info("{}.{} | args：{} ", className, methodName, JSON.toJSONString(args));
        String redisLockKey = "";
        //如果参数中有key
        if (args[0] instanceof BaseLockKey) {
            BaseLockKey baseLockGroupId = (BaseLockKey) args[0];
            redisLockKey = baseLockGroupId.getLockKey();
        }
        if (StringUtils.isBlank(redisLockKey)) {
            throw new BizException("获取redisLockKey失败");
        }
        log.info("开始加锁，redisLockKey：{}", redisLockKey);
        RLock lock = redissonClient.getLock(redisLockKey);
        Object result = null;
        if (lock.tryLock()) {
            try {
                //获取锁
                log.info("获取到锁，redisLockKey：{}", lock.getName());
                //获取到锁方法继续执行
                result = point.proceed();
            } finally {
                log.info("开始释放锁，redisLockKey：{}", redisLockKey);
                //判断是否持有锁
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        } else {
            throw new BizException("用户信息已锁定，请重新执行");
        }
        return result;
    }
}
