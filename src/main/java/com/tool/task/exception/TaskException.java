package com.tool.task.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskException extends RuntimeException{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String message;

    public TaskException(String message) {
        super(message);
        this.message = message;
        logger.error(this.message + "\t" + this);
    }

    @Override
    public String toString() {
        return "TaskException{" +
                "message='" + message + '\'' +
                '}';
    }

}
