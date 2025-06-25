package com.library.service;

import com.library.entity.Message;
import java.util.List;

public interface MessageService {
    List<Message> getMessagesByUserId(Integer userId);
    boolean markAsRead(Integer id);
    boolean sendMessage(Integer userId, String content, String type);
    boolean sendMessageToAdmins(Integer fromUserId, String content);
    Message getLatestMessageByUserId(Integer userId);
    int getReadCountByMessageId(Integer messageId);
} 