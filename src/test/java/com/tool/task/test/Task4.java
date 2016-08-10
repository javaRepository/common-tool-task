package com.tool.task.test;

import com.tool.task.taskhandle.TaskHelper;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class Task4 {

    private TaskHelper taskHelper;

    public void setTaskHelper(TaskHelper taskHelper) {
        this.taskHelper = taskHelper;
    }

    @Scheduled(cron="0/1 * * * * ?")
    public void excute4() {
        Boolean flag = taskHelper.isRunFastTask("testTask4", 1000);
        if(flag) {
            System.out.println((new Date() + " task4 run"));
        }
    }

}
