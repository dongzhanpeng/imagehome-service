package com.fly.imagehome.service.userhome.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly.imagehome.aop.annotation.LockCheck;
import com.fly.imagehome.constant.BasePageConstant;
import com.fly.imagehome.constant.CommonConstant;
import com.fly.imagehome.mapper.UserHomeMapper;
import com.fly.imagehome.pojo.BaseEntiy;
import com.fly.imagehome.pojo.UserHome;
import com.fly.imagehome.service.userhome.UserHomeService;
import com.fly.imagehome.utils.DozerConvertorUtil;
import com.fly.imagehome.utils.RedisUtil;
import com.fly.imagehome.vo.userhome.requestvo.LevelCodeRequestVo;
import com.fly.imagehome.vo.userhome.requestvo.UserHomeRequestVo;
import com.fly.imagehome.vo.userhome.requestvo.UserHomeUpdateRequestVo;
import com.fly.imagehome.vo.userhome.requestvo.UserNameRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @Classname UserServiceImpl
 * @Description UserServiceImpl
 * @Date 2021/12/7 14:20
 * @Author Fly
 * @Version 1.0
 */
@Service
@Slf4j
public class UserHomeServiceImpl implements UserHomeService {

    @Autowired
    private UserHomeMapper userHomeMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public UserHome getUserHomeById(Integer id) {
        log.info("根据用户id查询参数：{}", id);
        UserHome userHome = userHomeMapper.selectById(id);
        log.info("根据用户id：{}查询结果" + JSON.toJSONString(userHome), id);
        return userHome;
    }

    @Override
    public UserHome getUserHomeByUuid(String uuid) {
        log.info("根据用户uuid查询参数：{}", uuid);
        LambdaQueryWrapper<UserHome> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserHome::getUuid, uuid);
        UserHome userHome = userHomeMapper.selectOne(lambdaQueryWrapper);
        log.info("根据用户uuid：{}查询结果：" + JSON.toJSONString(userHome), uuid);
        return userHome;
    }

    @Override
    public IPage<UserHome> getUserHomeByName(UserNameRequestVo userNameRequestVo) {
        log.info("根据用户name查询参数：{}", JSON.toJSONString(userNameRequestVo));
        LambdaQueryWrapper<UserHome> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(UserHome::getUserName, userNameRequestVo.getUserNames());
        Page<UserHome> page = new Page<>(userNameRequestVo.getPageNumber(), userNameRequestVo.getPageSize());
        IPage<UserHome> iPage = userHomeMapper.selectPage(page, lambdaQueryWrapper);
        log.info("根据用户name：{}查询结果：" + JSON.toJSONString(iPage), userNameRequestVo.getUserNames());
        return iPage;

    }

    @Override
    public List<UserHome> getUserHomeByGroupId(String groupCode) {
        log.info("根据用户groupId查询参数：{}", groupCode);
        LambdaQueryWrapper<UserHome> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserHome::getGroupCode, groupCode);
        List<UserHome> userHomeList = userHomeMapper.selectList(lambdaQueryWrapper);

        //stream 操作
        //去重后计数
        Long count = userHomeList.stream().distinct().count();
        log.info("stream操作---count---count：{}", count);
        //Matching操作
        Boolean isValidAny = userHomeList.stream().anyMatch(userHome -> userHome.getUserName().contains("dong"));
        Boolean isValidAll = userHomeList.stream().allMatch(userHome -> userHome.getUserName().contains("dong"));
        Boolean isValidNone = userHomeList.stream().noneMatch(userHome -> userHome.getUserName().contains("dong"));
        log.info("stream操作---Matching---isValidAny：{}", isValidAny);
        log.info("stream操作---Matching---isValidAll：{}", isValidAll);
        log.info("stream操作---Matching---isValidNone：{}", isValidNone);
        //Filtering
        List<UserHome> userNameList = userHomeList.stream().filter(userHome -> userHome.getUserName().contains("dong")).collect(Collectors.toList());
        log.info("stream操作---Filtering---userNameList：{}", userNameList);
        //Mapping
        List<String> userName = userNameList.stream().map(userHome -> this.converterUserName(userHome.getUserName())).collect(Collectors.toList());
        log.info("stream操作---Mapping---userName：{}", userName);
        //Reduction
        List<Integer> integers = Arrays.asList(1, 2, 3);
        Integer reduce = integers.stream().reduce(100, Integer::sum);
        log.info("stream操作---Reduction---reduce：{}", reduce);
        log.info("根据用户groupId：{}查询结果：" + JSON.toJSONString(userHomeList), groupCode);
        return userHomeList;
    }

    /**
     * 字符串拼接
     *
//     * @param userName
     * @return
     */
    private String converterUserName(String userName) {
        return userName + "Fly";
    }


    @Override
    public IPage<UserHome> getUserHomeByLevelCode(LevelCodeRequestVo levelCodeRequestVo) {
        log.info("根据用户levelCode查询参数：{}", JSON.toJSONString(levelCodeRequestVo));
        LambdaQueryWrapper<UserHome> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(UserHome::getLevelCode, levelCodeRequestVo.getLevelCodes());
        Page<UserHome> page = new Page<>(levelCodeRequestVo.getPageNumber(), levelCodeRequestVo.getPageSize());
        IPage<UserHome> userHomeIPage = userHomeMapper.selectPage(page, lambdaQueryWrapper);
        log.info("根据用户levelCode：{}查询结果：" + JSON.toJSONString(userHomeIPage), levelCodeRequestVo.getLevelCodes());
        return userHomeIPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public UserHome createUserHome(UserHomeRequestVo userHomeRequestVo) {
        log.info("新增userhome入参：" + JSON.toJSONString(userHomeRequestVo));
        UserHome userHome = DozerConvertorUtil.convertor(userHomeRequestVo, UserHome.class);
        userHome.createInit();
        userHomeMapper.insert(userHome);
        log.info("新增userhome成功,id为{}：", userHome.getId());
        return userHomeMapper.selectById(userHome.getId());
    }

    @Override
    public List<String> getAllUser() {
        Object userObj = null;
        List<String> userList = new ArrayList<>();
        try {
            userObj = redisUtil.getObject(CommonConstant.REDIS_KEY_ALL_user);
        } catch (Exception e) {
            log.info("redis获取数据异常");
            e.printStackTrace();
        }
        if (Objects.nonNull(userObj)) {
            userList = (List<String>) userObj;
            log.info("获取人员数据from redis");
        } else {
            LambdaQueryWrapper<UserHome> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.select(UserHome::getUserName);
            List<UserHome> userHomeList = userHomeMapper.selectList(lambdaQueryWrapper);
            if (CollectionUtils.isNotEmpty(userHomeList)) {
                userList = userHomeList.stream().map(UserHome::getUserName).collect(Collectors.toList());
                log.info("获取人员数据from mysql");
                this.cacheAllUser(userList);
            }
        }
        return userList;
    }

    /**
     * 缓存数据到redis
     *
     * @param userList
     */
    private void cacheAllUser(List<String> userList) {
        try {
            if (CollectionUtils.isNotEmpty(userList)) {
                redisUtil.setObject(CommonConstant.REDIS_KEY_ALL_user, userList, CommonConstant.REDIS_KEY_EXPIRE_ONE_DAY);
                log.info("缓存人员数据to redis");
            }
        } catch (Exception e) {
            log.info("redis缓存数据异常");
            e.printStackTrace();
        }
    }

    @LockCheck
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public UserHome updateUserHome(UserHomeUpdateRequestVo userHomeUpdateRequestVo) {
        log.info("更新userhome入参：" + JSON.toJSONString(userHomeUpdateRequestVo));
        UserHome userHome = this.getUserHomeByUuid(userHomeUpdateRequestVo.getUuid());
        if (Objects.nonNull(userHome)) {
            BeanUtils.copyProperties(userHomeUpdateRequestVo, userHome);
            userHomeMapper.updateById(userHome);
        }
        return userHome;
    }

    @Override
    public IPage<UserHome> findList(BasePageConstant basePageConstant) {
        log.info("page：{},size：{}", basePageConstant.getPageNumber(), basePageConstant.getPageSize());
        LambdaQueryWrapper<UserHome> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.le(BaseEntiy::getId, 6000000);
        Page<UserHome> page = new Page<>(basePageConstant.getPageNumber(), basePageConstant.getPageSize());
        IPage<UserHome> pageList = userHomeMapper.selectPage(page, lambdaQueryWrapper);
        return pageList;
    }

    @Override
    public void deleteAllList() {

        Long startTime = System.currentTimeMillis();
        List<Long> idsList;
        BasePageConstant basePageConstant = new BasePageConstant();
        basePageConstant.setPageNumber(1);
        basePageConstant.setPageSize(1);
        LambdaQueryWrapper<UserHome> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<UserHome> page = new Page<>(basePageConstant.getPageNumber(), basePageConstant.getPageSize());
        IPage<UserHome> pageList = userHomeMapper.selectPage(page, lambdaQueryWrapper);

        for (int i = 1; i <= pageList.getPages(); i++) {
            page.setPages(i);
            page.setSize(10000);
            IPage<UserHome> pageLists = userHomeMapper.selectPage(page, lambdaQueryWrapper);
            idsList = pageLists.getRecords().stream().map(UserHome::getId).collect(Collectors.toList());
            List<Long> zeroIdsList = idsList.stream().filter(id -> id.toString().endsWith("0")).collect(Collectors.toList());
            List<Long> oneIdsList = idsList.stream().filter(id -> id.toString().endsWith("1")).collect(Collectors.toList());
            List<Long> twoIdsList = idsList.stream().filter(id -> id.toString().endsWith("2")).collect(Collectors.toList());
            List<Long> threeIdsList = idsList.stream().filter(id -> id.toString().endsWith("3")).collect(Collectors.toList());
            List<Long> fourIdsList = idsList.stream().filter(id -> id.toString().endsWith("4")).collect(Collectors.toList());
            List<Long> fiveIdsList = idsList.stream().filter(id -> id.toString().endsWith("5")).collect(Collectors.toList());
            List<Long> sixIdsList = idsList.stream().filter(id -> id.toString().endsWith("6")).collect(Collectors.toList());
            List<Long> sevenIdsList = idsList.stream().filter(id -> id.toString().endsWith("7")).collect(Collectors.toList());
            List<Long> eightIdsList = idsList.stream().filter(id -> id.toString().endsWith("8")).collect(Collectors.toList());
            List<Long> nineIdsList = idsList.stream().filter(id -> id.toString().endsWith("9")).collect(Collectors.toList());

            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为0线程删除数据开始-----------线程0-----id列表:{}", JSON.toJSONString(zeroIdsList));
                    userHomeMapper.deleteBatchIds(zeroIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为1线程删除数据开始-----------线程1-----id列表:{}", JSON.toJSONString(oneIdsList));
                    userHomeMapper.deleteBatchIds(oneIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为2线程删除数据开始-----------线程2-----id列表:{}", JSON.toJSONString(twoIdsList));
                    userHomeMapper.deleteBatchIds(twoIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为3线程删除数据开始-----------线程3-----id列表:{}", JSON.toJSONString(threeIdsList));
                    userHomeMapper.deleteBatchIds(threeIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为4线程删除数据开始-----------线程4-----id列表:{}", JSON.toJSONString(fourIdsList));
                    userHomeMapper.deleteBatchIds(fourIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为5线程删除数据开始-----------线程5-----id列表:{}", JSON.toJSONString(fiveIdsList));
                    userHomeMapper.deleteBatchIds(fiveIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为6线程删除数据开始-----------线程6-----id列表:{}", JSON.toJSONString(sixIdsList));
                    userHomeMapper.deleteBatchIds(sixIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为7线程删除数据开始-----------线程7-----id列表:{}", JSON.toJSONString(sevenIdsList));
                    userHomeMapper.deleteBatchIds(sevenIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为8线程删除数据开始-----------线程8-----id列表:{}", JSON.toJSONString(eightIdsList));
                    userHomeMapper.deleteBatchIds(eightIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
            threadPoolExecutor.execute(() -> {
                try {
                    log.info("末尾为9线程删除数据开始-----------线程9-----id列表:{}", JSON.toJSONString(nineIdsList));
                    userHomeMapper.deleteBatchIds(nineIdsList);
                } catch (Exception e) {
                    log.error("删除失败");
                }
            });
        }
        Long endTime = System.currentTimeMillis();
        log.info("批量数据删除time：{}", (endTime - startTime) + "ms");
    }
}
