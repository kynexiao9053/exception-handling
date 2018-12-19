package com.zhilehuo.exceptiondemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(value = "com.zhilehuo.exceptiondemo.mapper")
@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ExceptionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionDemoApplication.class, args);
    }
}
