package com.zhilehuo.exceptiondemo.exception;

/**
 * 持有一个UserExistsError错误枚举的业务异常，表示系统当前发生了"用户名已存在"的错误
 *
 * @author kaiqunxiao
 * @date 2018/11/24 下午4:32
 */
public class UserExistsException extends ServiceException{

    public UserExistsException(String message, Throwable cause) {
        super(SystemError.UserExistsError, message, cause);
    }

    public UserExistsException(String message){
        super(SystemError.UserExistsError, message);
    }
}