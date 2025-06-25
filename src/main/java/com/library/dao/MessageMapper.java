package com.library.dao;

import com.library.entity.Message;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface MessageMapper {
    List<Message> getMessagesByUserId(@Param("userId") Integer userId);
    int markAsRead(@Param("id") Integer id);
    int sendMessage(@Param("userId") Integer userId, @Param("content") String content, @Param("type") String type, @Param("fromUserId") Integer fromUserId);
    Message getLatestMessageByUserId(@Param("userId") Integer userId);
    int countReadByMessageId(@Param("messageId") Integer messageId);
    List<Message> getSentAdminMessages(@Param("fromUserId") Integer fromUserId);
    List<Message> getSentFeedbacks(@Param("fromUserId") Integer fromUserId);
    List<Map<String, Object>> queryMessages(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("type") String type, @Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("keyword") String keyword);
    int countMessages(@Param("type") String type, @Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd, @Param("keyword") String keyword);
    int existsUnreadMessage(@Param("userId") Integer userId, @Param("content") String content);
} 