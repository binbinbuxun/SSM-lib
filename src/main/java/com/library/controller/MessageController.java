package com.library.controller;

import com.library.entity.Message;
import com.library.service.MessageService;
import com.library.annotation.RequireAdmin;
import com.library.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Map<String, Object> getMyMessages(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer userId = ((com.library.entity.User) userObj).getId();
        List<Message> messages = messageService.getMessagesByUserId(userId);
        result.put("success", true);
        result.put("data", messages);
        return result;
    }

    @PostMapping("/read")
    public Map<String, Object> markAsRead(@RequestBody Map<String, Integer> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer id = payload.get("id");
        boolean success = messageService.markAsRead(id);
        result.put("success", success);
        result.put("msg", success ? "已标记为已读" : "操作失败");
        return result;
    }

    @PostMapping("/send")
    public Map<String, Object> sendMessage(@RequestBody Map<String, Object> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        Integer fromUserId = null;
        if (userObj != null) {
            com.library.entity.User user = (com.library.entity.User) userObj;
            // 只有管理员发送时写入fromUserId
            if ("ADMIN".equals(user.getRole())) {
                fromUserId = user.getId();
            }
        }
        Integer userId = (Integer) payload.get("userId");
        String content = (String) payload.get("content");
        String type = (String) payload.getOrDefault("type", "SYSTEM");
        boolean success = messageService.sendMessage(userId, content, type, fromUserId);
        result.put("success", success);
        result.put("msg", success ? "发送成功" : "发送失败");
        return result;
    }

    /**
     * 管理员群发个人消息
     */
    @RequireAdmin
    @PostMapping("/sendToAll")
    public Map<String, Object> sendMessageToAll(@RequestBody Map<String, Object> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        Integer fromUserId = null;
        if (userObj != null) {
            com.library.entity.User user = (com.library.entity.User) userObj;
            if ("ADMIN".equals(user.getRole())) {
                fromUserId = user.getId();
            }
        }
        String content = (String) payload.get("content");
        String type = (String) payload.getOrDefault("type", "ADMIN");
        int successCount = 0;
        int failCount = 0;
        // 先插入一条主消息（user_id为null）
        messageService.sendMessage(null, content, type, fromUserId);
        List<com.library.entity.User> users = userService.getAllUsers();
        for (com.library.entity.User user : users) {
            boolean success = messageService.sendMessage(user.getId(), content, type, fromUserId);
            if (success) successCount++;
            else failCount++;
        }
        result.put("success", true);
        result.put("msg", String.format("发送成功：%d，失败：%d", successCount, failCount));
        return result;
    }

    /**
     * 管理员批量发送个人消息
     */
    @RequireAdmin
    @PostMapping("/sendToUsers")
    public Map<String, Object> sendMessageToUsers(@RequestBody Map<String, Object> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        Integer fromUserId = null;
        if (userObj != null) {
            com.library.entity.User user = (com.library.entity.User) userObj;
            if ("ADMIN".equals(user.getRole())) {
                fromUserId = user.getId();
            }
        }
        Object userIdsObj = payload.get("userIds");
        String content = (String) payload.get("content");
        String type = (String) payload.getOrDefault("type", "SYSTEM");
        int successCount = 0;
        int failCount = 0;
        if (userIdsObj instanceof List<?>) {
            List<?> userIds = (List<?>) userIdsObj;
            for (Object idObj : userIds) {
                Integer userId = null;
                try { userId = Integer.parseInt(idObj.toString()); } catch (Exception ignore) {}
                if (userId != null) {
                    boolean success = messageService.sendMessage(userId, content, type, fromUserId);
                    if (success) successCount++;
                    else failCount++;
                }
            }
        }
        result.put("success", true);
        result.put("msg", String.format("发送成功：%d，失败：%d", successCount, failCount));
        return result;
    }

    /**
     * 用户发送消息/建议/反馈给所有管理员
     */
    @PostMapping("/feedback")
    public Map<String, Object> sendFeedback(@RequestBody Map<String, Object> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer fromUserId = ((com.library.entity.User) userObj).getId();
        String content = (String) payload.get("content");
        boolean success = messageService.sendMessageToAdmins(fromUserId, content);
        result.put("success", success);
        result.put("msg", success ? "反馈已发送，管理员会尽快处理！" : "发送失败");
        return result;
    }

    /**
     * 获取某条消息的已读人数
     */
    @GetMapping("/read-count")
    public Map<String, Object> getReadCount(@RequestParam("id") Integer messageId) {
        Map<String, Object> result = new HashMap<>();
        int count = 0;
        try {
            count = messageService.getReadCountByMessageId(messageId);
            result.put("success", true);
            result.put("data", count);
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", "查询失败");
        }
        return result;
    }

    /**
     * 管理员：获取自己发送的管理员消息
     */
    @GetMapping("/sent-admin")
    public Map<String, Object> getSentAdminMessages(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        com.library.entity.User user = (com.library.entity.User) userObj;
        if (!"ADMIN".equals(user.getRole())) {
            result.put("success", false);
            result.put("msg", "无权限");
            return result;
        }
        result.put("success", true);
        result.put("data", messageService.getSentAdminMessages(user.getId()));
        return result;
    }

    /**
     * 普通用户：获取自己发送的反馈
     */
    @GetMapping("/sent-feedback")
    public Map<String, Object> getSentFeedback(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        com.library.entity.User user = (com.library.entity.User) userObj;
        // 只查type=USER_TO_ADMIN，fromUserId=当前用户id
        List<Message> feedbacks = messageService.getSentFeedbacks(user.getId());
        result.put("success", true);
        result.put("data", feedbacks);
        return result;
    }

    /**
     * 消息管理：多条件分页查询
     */
    @RequireAdmin
    @GetMapping("/manage-list")
    public Map<String, Object> getManageMessageList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String dateStart,
            @RequestParam(required = false) String dateEnd,
            @RequestParam(required = false) String keyword) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = messageService.queryMessages(pageNum, pageSize, type, dateStart, dateEnd, keyword);
        int total = messageService.countMessages(type, dateStart, dateEnd, keyword);
        result.put("success", true);
        result.put("data", list);
        result.put("pageNum", pageNum);
        result.put("totalPages", (int)Math.ceil(total / (pageSize * 1.0)));
        return result;
    }
} 