package com.skill.exception;

/**
 * User: Shinelon
 * Date: 2016/6/17
 * 秒杀关闭异常
 */
public class SeckillCloseException extends  SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
