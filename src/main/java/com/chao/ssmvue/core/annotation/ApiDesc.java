package com.chao.ssmvue.core.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by LuZichao on  2018/12/7 9:43
 * 只是用于一个反射的测试练习
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiDesc {

    @AliasFor("apiDescription")
    String value() default "";

    @AliasFor("value")
    String apiDescription() default "";
}
