package com.fly.imagehome.aop.annotation;

import java.lang.annotation.*;

/**
 * @Classname LockCheck
 * @Description 上锁切入点注解 放在需要增强的方法上- 通用
 * @Date 2021/12/9 15:26
 * @Author Fly
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockCheck {
}