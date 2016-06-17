package com.skill.exception;

/**
 * User: Shinelon
 * Date: 2016/6/17
 * Time:${Time}
 */
public class SeckillException extends  RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
