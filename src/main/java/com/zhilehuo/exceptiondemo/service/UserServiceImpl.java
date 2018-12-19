package com.zhilehuo.exceptiondemo.service;

import com.zhilehuo.exceptiondemo.exception.UserExistsException;
import com.zhilehuo.exceptiondemo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 用户相关业务的服务层的实现
 *
 * @author kaiqunxiao
 * @date 2018/11/29 下午2:45
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserMapper mapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void register(String name) {
        Objects.requireNonNull(name);
        try {
            mapper.insert(name);
        } catch (DuplicateKeyException dke){
            // 遵守接口定义，构造一个业务异常并抛出
            // 注意写明message和传入cause，方便调试
            String message = "duplicate user name: " + name;
            throw new UserExistsException(message,dke);
        }
    }
}