package com.fly.imagehome.constant.inter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname SwaggerApiEnum
 * @Description TODO
 * @Date 2021/12/28 11:23
 * @Author Fly
 * @Version 1.0
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerApiEnum {
    Class<?> type() default Enum.class;

    String c() default "code";

    String v() default "name";
}