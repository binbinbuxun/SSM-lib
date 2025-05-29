package com.library.dao;

import com.library.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User selectByUsername(String username);

    int insertUser(User user);

    User login(@Param("username") String username, @Param("password") String password);

    int checkUsername(String username);
}

