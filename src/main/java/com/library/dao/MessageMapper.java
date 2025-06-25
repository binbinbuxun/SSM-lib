package com.library.dao;

import com.library.entity.Message;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface MessageMapper {
    List<Message> getMessagesByUserId(@Param("userId") Integer userId);
    int markAsRead(@Param("id") Integer id);
    int sendMessage(@Param("userId") Integer userId, @Param("content") String content, @Param("type") String type, @Param("fromUserId") Integer fromUserId);
    Message getLatestMessageByUserId(@Param("userId") Integer userId);
    int countReadByMessageId(@Param("messageId") Integer messageId);
} 