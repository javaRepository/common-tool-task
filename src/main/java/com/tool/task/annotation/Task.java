package com.tool.task.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Task {
    /**redis key*/
    String redisKey();
    /**key超时时间， 单位s*/
    long expireSecondTime();

}
