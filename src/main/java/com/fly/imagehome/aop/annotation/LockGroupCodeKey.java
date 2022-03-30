package com.fly.imagehome.aop.annotation;

import java.lang.annotation.*;

/**
 * @Classname LockGroupCodeKey
 * @Description 放在需要上锁的字段，例如用户uuid
 * @Date 2021/12/9 14:41
 * @Author Fly
 * @Version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockGroupCodeKey {

    /**
     * 默认值
     *
     * @return
     */
    String value() default "";
}
