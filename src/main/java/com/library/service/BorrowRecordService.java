package com.library.service;

import com.library.entity.BorrowRecord;
import com.library.entity.Book;
import java.util.List;

public interface BorrowRecordService {
    // 借阅图书
    boolean borrowBook(Integer userId, Integer bookId);

    // 归还图书
    boolean returnBook(Integer recordId);

    // 获取借阅记录
    BorrowRecord getBorrowRecord(Integer id);

    // 获取用户的所有借阅记录
    List<BorrowRecord> getUserBorrowRecords(Integer userId);

    // 获取用户当前借阅中的图书
    List<BorrowRecord> getUserCurrentBorrows(Integer userId);

    // 获取所有借阅记录
    List<BorrowRecord> getAllBorrowRecords(int pageNum, int pageSize, String keyword);

    // 获取借阅记录总数
    int getTotalBorrowRecords(String keyword);

    // 检查用户是否可以借阅（检查是否有逾期未还的图书）
    boolean canUserBorrow(Integer userId);

    // 检查图书是否可借
    boolean isBookAvailable(Integer bookId);

    // 查询借阅次数最多的前N本图书
    List<Book> getTopBorrowedBooks(int limit);
}
