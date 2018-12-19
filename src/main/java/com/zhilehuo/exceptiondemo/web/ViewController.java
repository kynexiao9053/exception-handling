package com.zhilehuo.exceptiondemo.web;

import com.zhilehuo.exceptiondemo.exception.ServiceException;
import com.zhilehuo.exceptiondemo.exception.SystemError;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这个类的方法抛出的异常将会被{@link com.zhilehuo.exceptiondemo.exception.GlobalExceptionHandler}
 * 类处理
 *
 * @author kaiqunxiao
 * @date 2018/11/26 下午2:52
 */

@Controller
@RequestMapping("/view")
@AllArgsConstructor
public class ViewController {

    @RequestMapping("/get")
    public Object get(){
        try {
            throw new RuntimeException();
        } catch (RuntimeException e){
            throw new ServiceException(SystemError.HospitalNotExistsError,
                    "医院没找到" + "id:111");
        }
    }
}