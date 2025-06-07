package com.library.service;

import com.library.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Integer id);

    boolean updateUser(User user);

    boolean deleteUser(Integer id);

    boolean updateUserStatus(Integer id, Integer status);

    // 添加登录和注册相关方法
    User login(String username, String password);

    boolean register(User user);
}
