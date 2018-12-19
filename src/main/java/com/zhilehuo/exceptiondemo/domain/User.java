package com.zhilehuo.exceptiondemo.domain;

import lombok.Data;

/**
 * 对应数据库user表的实体类
 *
 * @author kaiqunxiao
 * @date 2018/11/24 下午3:46
 */
@Data
public class User {

    /**
     * 用户id
     */
    private int id;

    /**
     * 用户名
     */
    private String name;

}