package com.library.controller;

import com.library.annotation.RequireAdmin;
import com.library.entity.User;
import com.library.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequireAdmin
    @GetMapping("/list")
    public Map<String, Object> getAllUsers() {
        Map<String, Object> result = new HashMap<>();
        List<User> users = userService.getAllUsers();
        result.put("success", true);
        result.put("data", users);
        result.put("msg", "查询成功");
        return result;
    }

    @RequireAdmin
    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.getUserById(id);
        result.put("success", user != null);
        result.put("data", user);
        result.put("msg", user != null ? "查询成功" : "用户不存在");
        return result;
    }

    @RequireAdmin
    @PutMapping("/update")
    public Map<String, Object> updateUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.updateUser(user);
        result.put("success", success);
        result.put("msg", success ? "更新成功" : "更新失败");
        return result;
    }

    @RequireAdmin
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.deleteUser(id);
        result.put("success", success);
        result.put("msg", success ? "删除成功" : "删除失败");
        return result;
    }

    @RequireAdmin
    @PutMapping("/{id}/status")
    public Map<String, Object> updateUserStatus(@PathVariable Integer id, @RequestParam Integer status) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.updateUserStatus(id, status);
        result.put("success", success);
        result.put("msg", success ? "状态更新成功" : "状态更新失败");
        return result;
    }

    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            session.setAttribute("user", loginUser);
            result.put("success", true);
            result.put("data", loginUser);
            result.put("role", loginUser.getRole());
            result.put("message", "登录成功");
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.register(user);
        result.put("success", success);
        result.put("msg", success ? "注册成功" : "注册失败，用户名可能已存在");
        return result;
    }
}
