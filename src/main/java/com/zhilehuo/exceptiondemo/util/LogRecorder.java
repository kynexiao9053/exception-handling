package com.zhilehuo.exceptiondemo.util;

import com.zhilehuo.exceptiondemo.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * 封装向日志库打印日志的方法实现
 *
 * @author kaiqunxiao
 * @date 2018/11/29 下午8:45
 */
@Component
public class LogRecorder {

    private Logger errLogger;

    @PostConstruct
    public void init(){
        errLogger = Logger.getLogger("error");
    }

    /**
     * 将异常信息打印到日志库中
     * @param e 待打印的日志
     * @param uuid 如果异常不是{@link ServiceException}类型，则使用此值作为errId打印;
     *                如果异常是{@link ServiceException}类型，可以不传入此值
     */
    public void error(Exception e, UUID uuid){
        if (e instanceof ServiceException){
            errLogger.error("errId:" + ((ServiceException) e).getUuid().toString() +
                    "\n" + ((ServiceException) e).getError().toString() +
                    "\n" + ExceptionUtils.getTraceInfo(e));
        } else {
            String errId = uuid != null ? uuid.toString() : null;
            errLogger.error("errId:" + errId + "\n" +
                    ExceptionUtils.getTraceInfo(e));
        }
    }

    /**
     * 打印业务异常信息到日志库中
     * @param e
     */
    public void error(ServiceException e){
        this.error(e, null);
    }
}