package com.library.service;

import com.library.entity.Message;
import java.util.List;
import java.util.Map;

public interface MessageService {
    List<Message> getMessagesByUserId(Integer userId);
    boolean markAsRead(Integer id);
    boolean sendMessage(Integer userId, String content, String type);
    boolean sendMessage(Integer userId, String content, String type, Integer fromUserId);
    boolean sendMessageToAdmins(Integer fromUserId, String content);
    Message getLatestMessageByUserId(Integer userId);
    int getReadCountByMessageId(Integer messageId);
    List<Message> getSentAdminMessages(Integer fromUserId);
    List<Message> getSentFeedbacks(Integer fromUserId);
    List<Map<String, Object>> queryMessages(int pageNum, int pageSize, String type, String dateStart, String dateEnd, String keyword);
    int countMessages(String type, String dateStart, String dateEnd, String keyword);
    boolean existsUnreadMessage(Integer userId, String content);
} 