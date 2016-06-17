package com.skill.exception;

/**
 * User: Shinelon
 * Date: 2016/6/17
 * Time:${Time}
 * 重复秒杀异常（运行期一场）
 */
public class RepeatKillException extends SeckillException{

    public  RepeatKillException (String message){
     super(message);
    }
    public RepeatKillException(String message,Throwable cause){
        super(message, cause);
    }
}
