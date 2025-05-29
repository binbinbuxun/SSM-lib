package com.library.service.impl;

import com.library.dao.UserMapper;
import com.library.entity.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        // 检查用户名是否已存在
        if (userMapper.checkUsername(user.getUsername()) > 0) {
            return false;
        }

        // 设置创建时间和更新时间
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        // 插入用户数据
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }
}
