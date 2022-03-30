package com.fly.imagehome.aop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fly.imagehome.aop.annotation.LockGroupCodeKey;
import com.fly.imagehome.aop.annotation.LockKey;
import com.fly.imagehome.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @Classname BaseLockGroupIdRequestVo
 * @Description BaseLockGroupIdRequestVo
 * @Date 2021/12/9 14:49
 * @Author Fly
 * @Version 1.0
 */
@Slf4j
public abstract class BaseLockKey {

    @JsonIgnore
    public String getLockKey() throws Exception {
        //获取类的所有字段(由public、private修饰的等等)
        Field[] fields = this.getClass().getDeclaredFields();
        String result = null;
        for (Field field : fields) {
            //获取LockKey注解信息
            LockKey lockKeyAnnotation = field.getDeclaredAnnotation(LockKey.class);
            if (Objects.nonNull(lockKeyAnnotation)) {
                //设置允许访问私有变量
                field.setAccessible(true);
                Object resultValue = field.get(this);
                log.info("反射获取到lockKey:{}", resultValue);
                if (Objects.isNull(resultValue)) {
                    throw new BizException("redisLockKey获取失败");
                }
                if (StringUtils.isNotBlank(this.getLockGroupCodeKey())) {
                    result = String.format("%s:%s:%s", lockKeyAnnotation.prefix(), getLockGroupCodeKey(), resultValue);
                } else {
                    result = String.format("%s:%s:%s", lockKeyAnnotation.prefix(), lockKeyAnnotation.type(), resultValue);
                }
            }
        }
        return result;
    }

    /**
     * 获取key来源类型，区分各个系统
     *
     * @return
     * @throws Exception
     */
    @JsonIgnore
    public String getLockGroupCodeKey() throws Exception {
        Field[] fields = this.getClass().getDeclaredFields();
        String type = "";
        for (Field field : fields) {
            LockGroupCodeKey lockKeyTypeAnnotation = field.getDeclaredAnnotation(LockGroupCodeKey.class);
            if (Objects.nonNull(lockKeyTypeAnnotation)) {
                field.setAccessible(true);
                // 获取人员分组信息
                Object resultValue = field.get(this);
                if (Objects.isNull(resultValue)) {
                    throw new BizException("GroupId不能为空");
                }
                String lockKeyType = lockKeyTypeAnnotation.value();
                if (StringUtils.isNotBlank(lockKeyType)) {
                    type = lockKeyType;
                } else {
                    type = resultValue.toString();
                }
                return type;
            }
        }
        log.info("反射获取到LockGroupCodeKey：{}", type);
        return type;
    }
}
