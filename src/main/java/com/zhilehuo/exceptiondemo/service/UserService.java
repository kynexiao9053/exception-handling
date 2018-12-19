package com.zhilehuo.exceptiondemo.service;

/**
 * 用户相关业务的服务层接口定义
 *
 * @author kaiqunxiao
 * @date 2018/11/29 下午2:45
 */
public interface UserService {

    /**
     * 注册用户，将用户名入库
     *
     * @param name 新注册的用户名
     * @throws NullPointerException 用户名为null时抛出
     * @throws com.zhilehuo.exceptiondemo.exception.UserExistsException 注册的用户名已重复时抛出
     */
    void register(String name);
}