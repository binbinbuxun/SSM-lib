package com.library.controller;

import com.library.annotation.RequireAdmin;
import com.library.entity.User;
import com.library.service.UserService;
import com.library.service.BorrowRecordService;
import com.library.service.impl.ReservationTaskService;
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

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private ReservationTaskService reservationTaskService;

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
            borrowRecordService.checkDueRemindersForUser(loginUser.getId());
            reservationTaskService.checkReservationNotifyForUser(loginUser.getId());
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

    @GetMapping("/profile")
    public Map<String, Object> getProfile(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        User user = (User) userObj;
        result.put("success", true);
        result.put("data", user);
        return result;
    }

    @PutMapping("/profile")
    public Map<String, Object> updateProfile(@RequestBody User user, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        User currentUser = (User) userObj;
        if (!currentUser.getId().equals(user.getId())) {
            result.put("success", false);
            result.put("msg", "无权修改其他用户信息");
            return result;
        }
        // 只允许修改age和gender
        currentUser.setAge(user.getAge());
        currentUser.setGender(user.getGender());
        boolean success = userService.updateUserProfile(currentUser);
        if (success) {
            session.setAttribute("user", currentUser);
        }
        result.put("success", success);
        result.put("msg", success ? "更新成功" : "更新失败");
        return result;
    }

    @PutMapping("/password")
    public Map<String, Object> updatePassword(@RequestBody Map<String, String> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        User currentUser = (User) userObj;
        
        String oldPassword = params.get("oldPassword");
        String newPassword = params.get("newPassword");
        
        if (oldPassword == null || newPassword == null) {
            result.put("success", false);
            result.put("msg", "参数不完整");
            return result;
        }
        
        // 验证旧密码
        if (!currentUser.getPassword().equals(oldPassword)) {
            result.put("success", false);
            result.put("msg", "旧密码错误");
            return result;
        }
        
        // 更新密码
        currentUser.setPassword(newPassword);
        boolean success = userService.updateUser(currentUser);
        if (success) {
            session.setAttribute("user", currentUser);
        }
        result.put("success", success);
        result.put("msg", success ? "密码修改成功" : "密码修改失败");
        return result;
    }

    @GetMapping("/borrow-rank")
    public Map<String, Object> getUserBorrowRank() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> rankList = userService.getUserBorrowRank(5);
        result.put("success", true);
        result.put("data", rankList);
        return result;
    }

    @GetMapping("/count")
    public Map<String, Object> getUserCount() {
        Map<String, Object> result = new HashMap<>();
        int count = userService.getAllUsers().size();
        result.put("success", true);
        result.put("data", count);
        return result;
    }
}
