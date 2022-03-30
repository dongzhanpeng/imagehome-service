package com.fly.imagehome.constant;

/**
 * @Classname CommonConstant
 * @Description 常量接口
 * @Date 2021/12/8 15:45
 * @Author Fly
 * @Version 1.0
 */
public interface CommonConstant {

    /**
     * username缓存键
     */
    String REDIS_KEY_ALL_user = "fly:imagehome:basicdata:username:all";

    /**
     * 缓存过期时间-1天
     */
    int REDIS_KEY_EXPIRE_ONE_DAY = 60 * 60 * 12;
}
