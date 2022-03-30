package com.fly.imagehome.utils;

import java.util.UUID;

/**
 * @Classname UuidUtil
 * @Description uuid工具类
 * @Date 2021/12/8 13:41
 * @Author Fly
 * @Version 1.0
 */
public class UuidUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
