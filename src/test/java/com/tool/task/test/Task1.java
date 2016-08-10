package com.tool.task.test;

import com.tool.task.annotation.Task;
import com.tool.task.taskhandle.TaskHelper;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class Task1 {

    private TaskHelper taskHelper;

    @Scheduled(cron="0/4 * * * * ?")
    @Task(redisKey="testTask1", expireSecondTime = 4)
    public void excute() {
        Boolean flag = taskHelper.isRun(Task1.class);
        if(flag) {
            System.out.println((new Date() + "  task1 run"));
        }
    }

    public void setTaskHelper(TaskHelper taskHelper) {
        this.taskHelper = taskHelper;
    }

}
