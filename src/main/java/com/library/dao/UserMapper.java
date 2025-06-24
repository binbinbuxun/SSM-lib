package com.library.dao;

import com.library.entity.User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper {
    List<User> getAllUsers();

    User getUserById(@Param("id") Integer id);

    int updateUser(User user);

    int deleteUser(@Param("id") Integer id);

    int updateUserStatus(@Param("id") Integer id, @Param("status") Integer status);

    // 添加登录和注册相关方法
    User getUserByUsername(@Param("username") String username);

    int insertUser(User user);

    User login(@Param("username") String username, @Param("password") String password);

    int updateUserProfile(User user);
}
