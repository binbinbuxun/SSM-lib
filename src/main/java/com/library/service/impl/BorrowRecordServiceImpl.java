package com.library.service.impl;

import com.library.dao.BorrowRecordMapper;
import com.library.dao.BookMapper;
import com.library.entity.BorrowRecord;
import com.library.entity.Book;
import com.library.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    @Transactional
    public boolean borrowBook(Integer userId, Integer bookId) {
        // 检查用户是否可以借阅
        if (!canUserBorrow(userId)) {
            return false;
        }

        // 检查图书是否可借
        if (!isBookAvailable(bookId)) {
            return false;
        }

        // 创建借阅记录
        BorrowRecord record = new BorrowRecord();
        record.setUserId(userId);
        record.setBookId(bookId);
        record.setBorrowDate(new Date());
        
        // 设置归还日期（默认30天）
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        record.setDueDate(calendar.getTime());
        
        record.setStatus(0); // 设置状态为借阅中

        // 更新图书库存
        Book book = bookMapper.getBookById(bookId);
        if (book != null && book.getStock() > 0) {
            bookMapper.updateStock(bookId, book.getStock() - 1);
            return borrowRecordMapper.insertBorrowRecord(record) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean returnBook(Integer recordId) {
        BorrowRecord record = borrowRecordMapper.getBorrowRecordById(recordId);
        if (record == null || record.getStatus() != 0) {
            return false;
        }

        // 更新借阅记录
        record.setReturnDate(new Date());
        record.setStatus(1); // 设置状态为已归还

        // 更新图书库存
        Book book = bookMapper.getBookById(record.getBookId());
        if (book != null) {
            bookMapper.updateStock(record.getBookId(), book.getStock() + 1);
            return borrowRecordMapper.updateBorrowRecord(record) > 0;
        }
        return false;
    }

    @Override
    public List<BorrowRecord> getUserBorrowRecords(Integer userId) {
        return borrowRecordMapper.getBorrowRecordsByUserId(userId);
    }

    @Override
    public List<BorrowRecord> getUserCurrentBorrows(Integer userId) {
        return borrowRecordMapper.getCurrentBorrowRecordsByUserId(userId);
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecords(int pageNum, int pageSize, String keyword) {
        int offset = (pageNum - 1) * pageSize;
        return borrowRecordMapper.getAllBorrowRecords(offset, pageSize, keyword);
    }

    @Override
    public int getTotalBorrowRecords(String keyword) {
        return borrowRecordMapper.getTotalBorrowRecords(keyword);
    }

    @Override
    public boolean canUserBorrow(Integer userId) {
        // 检查用户是否有逾期未还的图书
        List<BorrowRecord> currentBorrows = borrowRecordMapper.getCurrentBorrowRecordsByUserId(userId);
        Date now = new Date();
        
        // 检查是否有逾期未还的图书
        for (BorrowRecord record : currentBorrows) {
            if (record.getDueDate().before(now)) {
                return false;
            }
        }
        
        // 检查当前借阅数量是否达到上限（3本）
        if (currentBorrows.size() >= 3) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean isBookAvailable(Integer bookId) {
        Book book = bookMapper.getBookById(bookId);
        return book != null && book.getStock() > 0;
    }

    @Override
    public BorrowRecord getBorrowRecord(Integer id) {
        return borrowRecordMapper.getBorrowRecordById(id);
    }

    @Override
    public List<Book> getTopBorrowedBooks(int limit) {
        return borrowRecordMapper.getTopBorrowedBooks(limit);
    }
}
