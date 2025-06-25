package com.library.service.impl;

import com.library.dao.MessageMapper;
import com.library.dao.UserMapper;
import com.library.entity.Message;
import com.library.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Message> getMessagesByUserId(Integer userId) {
        return messageMapper.getMessagesByUserId(userId);
    }

    @Override
    public boolean markAsRead(Integer id) {
        return messageMapper.markAsRead(id) > 0;
    }

    @Override
    public boolean sendMessage(Integer userId, String content, String type) {
        // 兼容旧用法，fromUserId为null
        return sendMessage(userId, content, type, null);
    }

    @Override
    public boolean sendMessage(Integer userId, String content, String type, Integer fromUserId) {
        return messageMapper.sendMessage(userId, content, type, fromUserId) > 0;
    }

    @Override
    public boolean sendMessageToAdmins(Integer fromUserId, String content) {
        // 查询所有管理员
        List<com.library.entity.User> admins = userMapper.getAllAdmins();
        boolean allSuccess = true;
        for (com.library.entity.User admin : admins) {
            // type为USER_TO_ADMIN，fromUserId为发送者
            boolean success = messageMapper.sendMessage(admin.getId(), content, "USER_TO_ADMIN", fromUserId) > 0;
            if (!success) allSuccess = false;
        }
        return allSuccess;
    }

    @Override
    public Message getLatestMessageByUserId(Integer userId) {
        return messageMapper.getLatestMessageByUserId(userId);
    }

    @Override
    public int getReadCountByMessageId(Integer messageId) {
        return messageMapper.countReadByMessageId(messageId);
    }

    @Override
    public List<Message> getSentAdminMessages(Integer fromUserId) {
        return messageMapper.getSentAdminMessages(fromUserId);
    }

    @Override
    public List<Message> getSentFeedbacks(Integer fromUserId) {
        return messageMapper.getSentFeedbacks(fromUserId);
    }

    @Override
    public List<Map<String, Object>> queryMessages(int pageNum, int pageSize, String type, String dateStart, String dateEnd, String keyword) {
        int offset = (pageNum - 1) * pageSize;
        return messageMapper.queryMessages(offset, pageSize, type, dateStart, dateEnd, keyword);
    }

    @Override
    public int countMessages(String type, String dateStart, String dateEnd, String keyword) {
        return messageMapper.countMessages(type, dateStart, dateEnd, keyword);
    }

    @Override
    public boolean existsUnreadMessage(Integer userId, String content) {
        return messageMapper.existsUnreadMessage(userId, content) > 0;
    }
} 