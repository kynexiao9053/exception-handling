package com.zhilehuo.exceptiondemo.exception;

import lombok.Getter;

import java.util.UUID;

/**
 * 封装系统业务异常对象，在系统中向上抛出，直到被处理。
 *
 * <p>此类的对象持有一个{@link SystemError}枚举值。</p>
 *
 * <p>此类对象持有一个随机生成的UUID，建议将其打印到错误日志中并返回给前端，便于根据反馈定位
 * 后台异常</p>
 *
 * <p>可以在继承此类的子类中直接指定持有的枚举值，例如{@link UserExistsException}，
 * 它表示一个比业务异常更具体的异常，便于上层调用者的捕获和处理</p>
 *
 * @author kaiqunxiao
 * @date 2018/11/23
 */

@Getter
public class ServiceException extends RuntimeException{

    /**
     * 持有的系统错误枚举
     */
    private final SystemError error;

    /**
     * 异常ID
     */
    private final UUID uuid;

    public ServiceException(SystemError error){
        this.error = error;
        uuid = UUID.randomUUID();
    }

    public ServiceException(SystemError error, String message){
        super(message);
        this.error = error;
        uuid = UUID.randomUUID();
    }

    public ServiceException(SystemError error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
        uuid = UUID.randomUUID();
    }

}