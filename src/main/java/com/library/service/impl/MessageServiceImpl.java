package com.library.service.impl;

import com.library.dao.MessageMapper;
import com.library.dao.UserMapper;
import com.library.entity.Message;
import com.library.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // 系统/管理员发消息，fromUserId为null
        return messageMapper.sendMessage(userId, content, type, null) > 0;
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
} 