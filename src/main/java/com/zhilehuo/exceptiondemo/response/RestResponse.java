package com.zhilehuo.exceptiondemo.response;

import com.zhilehuo.exceptiondemo.exception.SystemError;
import lombok.Data;

import java.util.UUID;

/**
 * 封装RESTFUL接口的响应体
 *
 * @author kaiqunxiao
 * @date 2018/11/29 下午2:45
 */
@Data
public class RestResponse {

    /**
     * 请求结果，可能的值：ok / error
     */
    private String result;

    /**
     * [请求失败]错误id，用于定位后台异常
     */
    private String errId;

    /**
     * [请求失败]错误码
     */
    private long errCode;

    /**
     * [请求失败]错误信息，与错误码一一对应
     */
    private String errMsg;

    /**
     * [请求失败]给用户的恢复/求助建议
     */
    private String recover;

    /**
     * [请求成功]响应数据
     */
    private Object data;

    /**
     * 生成一个错误响应实体
     * @param error
     * @param uuid
     * @return
     */
    public static RestResponse error(SystemError error, UUID uuid){
        RestResponse rr = new RestResponse();
        rr.result = "error";
        rr.errCode = error.getErrCode();
        rr.errMsg = error.getErrMsg();
        rr.recover = error.getRecover();
        rr.errId = uuid.toString();
        return rr;
    }

    /**
     * 构造一个成功响应实体
     * @param data
     * @return
     */
    public static RestResponse ok(Object data){
        RestResponse rr = new RestResponse();
        rr.result = "ok";
        rr.data = data;
        return rr;
    }

    /**
     * 构造一个成功响应实体
     * @return
     */
    public static RestResponse ok(){
        RestResponse rr = new RestResponse();
        rr.result = "ok";
        return rr;
    }
}