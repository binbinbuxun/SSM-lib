package com.library.controller;

import com.library.annotation.RequireAdmin;
import com.library.entity.BorrowRecord;
import com.library.service.BorrowRecordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/borrow")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    // 借阅图书
    @PostMapping("/borrow")
    public Map<String, Object> borrowBook(@RequestParam Integer bookId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }

        com.library.entity.User user = (com.library.entity.User) userObj;
        
        // 检查用户是否可以借阅
        if (!borrowRecordService.canUserBorrow(user.getId())) {
            List<BorrowRecord> currentBorrows = borrowRecordService.getUserCurrentBorrows(user.getId());
            if (currentBorrows.size() >= 3) {
                result.put("success", false);
                result.put("msg", "您当前已借阅3本书，请先归还部分图书后再借阅");
                return result;
            }
            
            // 检查是否有逾期未还的图书
            Date now = new Date();
            for (BorrowRecord record : currentBorrows) {
                if (record.getDueDate().before(now)) {
                    result.put("success", false);
                    result.put("msg", "您有逾期未还的图书，请先归还后再借阅");
                    return result;
                }
            }
        }
        
        // 检查图书是否可借
        if (!borrowRecordService.isBookAvailable(bookId)) {
            result.put("success", false);
            result.put("msg", "该图书当前不可借阅");
            return result;
        }

        boolean success = borrowRecordService.borrowBook(user.getId(), bookId);
        result.put("success", success);
        result.put("msg", success ? "借阅成功" : "借阅失败");
        return result;
    }

    // 归还图书
    @PostMapping("/return/{recordId}")
    public Map<String, Object> returnBook(@PathVariable Integer recordId) {
        Map<String, Object> result = new HashMap<>();
        boolean success = borrowRecordService.returnBook(recordId);
        result.put("success", success);
        result.put("msg", success ? "归还成功" : "归还失败");
        return result;
    }

    // 获取用户的借阅记录
    @GetMapping("/user/records")
    public Map<String, Object> getUserBorrowRecords(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }

        com.library.entity.User user = (com.library.entity.User) userObj;
        List<BorrowRecord> records = borrowRecordService.getUserBorrowRecords(user.getId());
        result.put("success", true);
        result.put("data", records);
        return result;
    }

    // 获取用户当前借阅的图书
    @GetMapping("/user/current")
    public Map<String, Object> getUserCurrentBorrows(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }

        com.library.entity.User user = (com.library.entity.User) userObj;
        List<BorrowRecord> records = borrowRecordService.getUserCurrentBorrows(user.getId());
        result.put("success", true);
        result.put("data", records);
        return result;
    }

    // 分页查询所有借阅记录（管理员功能）
    @RequireAdmin
    @GetMapping("/list")
    public Map<String, Object> getAllBorrowRecords(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        Map<String, Object> result = new HashMap<>();
        List<BorrowRecord> records = borrowRecordService.getAllBorrowRecords(pageNum, pageSize, keyword);
        int total = borrowRecordService.getTotalBorrowRecords(keyword);
        result.put("success", true);
        result.put("data", records);
        result.put("total", total);
        return result;
    }

    // 获取借阅排行榜（前5名）
    @GetMapping("/top-books")
    public List<com.library.entity.Book> getTopBorrowedBooks() {
        return borrowRecordService.getTopBorrowedBooks(5);
    }
}
