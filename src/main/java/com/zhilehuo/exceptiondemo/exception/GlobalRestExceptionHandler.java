package com.zhilehuo.exceptiondemo.exception;

import com.zhilehuo.exceptiondemo.response.RestResponse;
import com.zhilehuo.exceptiondemo.util.LogRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

/**
 * 负责处理web层RESTFUL类型的接口抛出的全局异常
 *
 * @author kaiqunxiao
 * @date 2018/11/26 下午12:40
 */
@Order(1)
@RestControllerAdvice(annotations = RestController.class)
public class GlobalRestExceptionHandler {

    @Autowired
    private LogRecorder logRecorder;

    /**
     * 处理ServiceException异常，将其中的errCode、errMsg、recover、错误id封装到响应对象中。
     * 向日志库打印日志
     * 最终返回报错的json响应
     *
     * 注意：此方法优先于另一个参数为RuntimeException类型的方法执行
     *
     * @param e 下层抛出的异常对象
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public Object handleServiceException(ServiceException e){
        logRecorder.error(e);
        return RestResponse.error(e.getError(), e.getUuid());
    }

    /**
     * 处理RuntimeException异常，将默认错误信息和错误id封装到响应对象中。
     * 向日志库打印日志
     * 最终返回报错的json响应
     *
     * 注意：此方法次于另一个参数为RuntimeException类型的方法执行
     *
     * @param e 下层抛出的异常对象
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Object handleRuntimeException(RuntimeException e){
        UUID uuid = UUID.randomUUID();
        logRecorder.error(e, uuid);
        return RestResponse.error(SystemError.SystemInternalError, uuid);
    }
}