package com.zhilehuo.exceptiondemo.web;

import com.zhilehuo.exceptiondemo.exception.UserExistsException;
import com.zhilehuo.exceptiondemo.response.RestResponse;
import com.zhilehuo.exceptiondemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 这个类的方法抛出的异常将会被{@link com.zhilehuo.exceptiondemo.exception.GlobalRestExceptionHandler}
 * 类处理
 *
 * @author kaiqunxiao
 * @date 2018/11/26 下午12:40
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class RestController {

    private UserService service;

    /**
     * 注册一个用户，多次传入同一参数将会发送"用户已存在"的错误
     *
     * @param name 注册用的用户名
     * @return
     */
    @PostMapping("/register")
    public Object get(@RequestParam("name") String name){
        try {
            service.register(name);
        } catch (UserExistsException e){
            // 在这里做用户已存在情况的独有逻辑
            // 如：发送一个报警邮件等
            // ...

            // 继续向上抛出异常，让全局处理逻辑来处理
            throw e;
        }
        return RestResponse.ok();
    }
}