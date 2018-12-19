package com.zhilehuo.exceptiondemo.exception;

import com.zhilehuo.exceptiondemo.util.LogRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

/**
 * 负责处理web层模板引擎类接口抛出的全局异常
 *
 * @author kaiqunxiao
 * @date 2018/11/24 下午3:46
 */
@Order(2)
@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandler {

    @Autowired
    private LogRecorder logRecorder;

    /**
     * 处理ServiceException异常，将其中的errCode、errMsg、recover、错误ID
     * 封装到model中。
     *
     * <p>向日志库打印日志</p>
     *
     * <p>最终跳转到错误页面</p>
     *
     * <p>注意：此方法优先于另一个参数为RuntimeException类型的方法执行</p>
     *
     * @param e 下层抛出的异常对象
     * @param model
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(ServiceException e, Model model){
        logRecorder.error(e);
        model.addAttribute("errCode",e.getError().getErrCode());
        model.addAttribute("errMsg",e.getError().getErrMsg());
        model.addAttribute("recover",e.getError().getRecover());
        model.addAttribute("errId",e.getUuid().toString());
        return "my-error";
    }

    /**
     * 处理RuntimeException异常，将默认错误信息和错误ID封装到model中。
     *
     * <p>向日志库打印日志</p>
     *
     * <p>最终跳转到错误页面</p>
     *
     * <p>注意：此方法次于另一个参数为ServiceException类型的方法执行</p>
     *
     * @param e 下层抛出的异常对象
     * @param model
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, Model model){
        UUID uuid = UUID.randomUUID();
        logRecorder.error(e, uuid);
        model.addAttribute("errCode","50000");
        model.addAttribute("errMsg","系统内部错误");
        model.addAttribute("recover","您可以在群内或投诉渠道反馈此问题，" +
                "我们将会安排专人为您解决");
        model.addAttribute("errId",uuid.toString());
        return "my-error";
    }
}