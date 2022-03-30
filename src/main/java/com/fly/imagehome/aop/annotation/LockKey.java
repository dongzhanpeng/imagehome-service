package com.fly.imagehome.aop.annotation;

import java.lang.annotation.*;

/**
 * @Classname LockKey
 * @Description 放在需要上锁的字段，例如用户uuid
 * @Date 2021/12/9 14:34
 * @Author Fly
 * @Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockKey {

    /**
     * redislock前缀
     *
     * @return
     */
    String prefix() default "Image_Home";

    /**
     * 目录。建议根据系统应用区分
     *
     * @return
     */
    String type() default "COMMON";

}
