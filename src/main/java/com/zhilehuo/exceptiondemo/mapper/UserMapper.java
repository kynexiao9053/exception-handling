package com.zhilehuo.exceptiondemo.mapper;


import org.apache.ibatis.annotations.Param;

/**
 * 用户实体的dao层接口定义
 *
 * @author kaiqunxiao
 * @date
 */
public interface UserMapper {

    /**
     * 向数据库中插入一条User记录
     *
     * @param name 用户名
     * @return 影响的行数
     */
    int insert(@Param("name") String name);
}