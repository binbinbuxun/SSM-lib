package com.library.service.impl;

import com.library.dao.UserMapper;
import com.library.entity.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user) > 0;
    }

    @Override
    public boolean deleteUser(Integer id) {
        return userMapper.deleteUser(id) > 0;
    }

    @Override
    public boolean updateUserStatus(Integer id, Integer status) {
        return userMapper.updateUserStatus(id, status) > 0;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        // 检查密码是否匹配
        if (!user.getPassword().equals(password)) {
            return null;
        }
        // 检查用户状态是否启用
        if (user.getStatus() == null || user.getStatus() != 1) {
            return null;
        }
        return user;
    }

    @Override
    public boolean register(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            return false;
        }
        // 设置默认状态为启用
        user.setStatus(1);
        // 设置默认角色
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER");
        }
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public boolean updateUserProfile(User user) {
        return userMapper.updateUserProfile(user) > 0;
    }

    @Override
    public List<java.util.Map<String, Object>> getUserBorrowRank(int limit) {
        return userMapper.getUserBorrowRank(limit);
    }
}
