package com.library.dao;

import com.library.entity.BorrowRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BorrowRecordMapper {
    // 添加借阅记录
    int insertBorrowRecord(BorrowRecord record);

    // 更新借阅记录
    int updateBorrowRecord(BorrowRecord record);

    // 根据ID查询借阅记录
    BorrowRecord getBorrowRecordById(@Param("id") Integer id);

    // 查询用户的所有借阅记录
    List<BorrowRecord> getBorrowRecordsByUserId(@Param("userId") Integer userId);

    // 查询图书的所有借阅记录
    List<BorrowRecord> getBorrowRecordsByBookId(@Param("bookId") Integer bookId);

    // 查询用户当前借阅中的图书
    List<BorrowRecord> getCurrentBorrowRecordsByUserId(@Param("userId") Integer userId);

    // 查询所有借阅记录
    List<BorrowRecord> getAllBorrowRecords(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("keyword") String keyword);

    // 获取借阅记录总数
    int getTotalBorrowRecords(@Param("keyword") String keyword);

    // 查询用户借阅的图书数量
    int getBorrowedBookCount(@Param("userId") Integer userId);
} 