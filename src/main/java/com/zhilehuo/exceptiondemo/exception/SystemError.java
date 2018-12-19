package com.zhilehuo.exceptiondemo.exception;

import lombok.ToString;

/**
 * 系统业务中可能发生的所有错误类型的枚举，包含错误码、错误原因、问题恢复建议。
 *
 * @author kaiqunxiao
 * @date
 */
@ToString
public enum SystemError {

    UserNotExistsError(10001,"用户不存在","请尝试重新扫码关注公众号，或在群内反馈您遇到的问题"),
    HospitalNotExistsError(10002,"您访问的医院不存在","如果您是医生，请联系客服重新开通您的医院服务"),
    UserExistsError(10003,"用户名已存在","您想要注册的用户名已存在，无法重复注册"),
    SystemInternalError(50000,"系统内部错误","您可以在群内或投诉渠道反馈此问题，我们将会安排专人为您解决");

    /**
     * 错误码，内部沟通使用
     */
    private long errCode;

    /**
     * 错误信息，返回给前端向用户展示，错误的简单解释
     */
    private String errMsg;

    /**
     * 恢复方法，告诉用户可能的从错误中恢复的方式，或者是求助方法
     */
    private String recover;

    SystemError(long errCode, String errMsg, String recover){
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.recover = recover;
    }

    public long getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public String getRecover() {
        return recover;
    }

}