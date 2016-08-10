package com.tool.task.taskhandle;

import com.tool.task.exception.TaskException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 使用注意TaskHelper isRun(Class<?> taskClazz)注意
 * 1、服务器时间同步
 * 2、redisKey唯一
 * 3、expireTime请设置为任务时间
 */
public class TaskHelperBo {
    /**
     * 使用此方法注意
     * 1、同一个类中可以定义多个任务
     * 2、expireTime 单位为s
     */
    public static boolean isRun(RedisTemplate<String, String> redisTemplate, String redisKey, long intervalTime) {
        if(redisTemplate == null || redisKey==null || intervalTime < 0) {
            throw new TaskException("参数错误");
        }
        Boolean setKeyFlag = redisTemplate.opsForValue().setIfAbsent(redisKey, redisKey);
        redisTemplate.expire(redisKey, intervalTime/2, TimeUnit.SECONDS);
        return setKeyFlag;
    }

}
