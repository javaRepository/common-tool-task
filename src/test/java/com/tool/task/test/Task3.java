package com.tool.task.test;

import com.tool.task.taskhandle.TaskHelperBo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class Task3 {

    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(cron="0/4 * * * * ?")
    public void excute2() {
        Boolean flag = TaskHelperBo.isRun(redisTemplate, "testTask3", 4);
        if(flag) {
            System.out.println((new Date() + " task3 run"));
        }
    }

}
