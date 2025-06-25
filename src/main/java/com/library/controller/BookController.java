package com.library.controller;

import com.library.annotation.RequireAdmin;
import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // 查询图书列表不需要管理员权限
    @GetMapping("/list")
    public Map<String, Object> getAllBooks() {
        Map<String, Object> result = new HashMap<>();
        List<Book> books = bookService.getAllBooks();
        result.put("success", true);
        result.put("data", books);
        result.put("msg", "查询成功");
        return result;
    }

    // 分页查询不需要管理员权限
    @GetMapping("/page")
    public Map<String, Object> getBooksByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Map<String, Object> result = new HashMap<>();
        List<Book> books = bookService.getBooksByPage(pageNum, pageSize);
        int total = bookService.getTotalBooks();
        result.put("success", true);
        result.put("data", books);
        result.put("total", total);
        result.put("pageNum", pageNum);
        result.put("pageSize", pageSize);
        return result;
    }

    // 搜索图书不需要管理员权限
    @GetMapping("/search")
    public Map<String, Object> searchBooks(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category) {
        Map<String, Object> result = new HashMap<>();
        List<Book> books = bookService.searchBooks(keyword, category);
        result.put("success", true);
        result.put("data", books);
        result.put("msg", "查询成功");
        return result;
    }

    // 查询单本图书不需要管理员权限
    @GetMapping("/{id}")
    public Map<String, Object> getBook(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        Book book = bookService.getBookById(id);
        result.put("success", book != null);
        result.put("data", book);
        result.put("msg", book != null ? "查询成功" : "图书不存在");
        return result;
    }

    // 添加图书需要管理员权限
    @RequireAdmin
    @PostMapping("/add")
    public Map<String, Object> addBook(@RequestBody Book book) {
        Map<String, Object> result = new HashMap<>();
        boolean success = bookService.addBook(book);
        result.put("success", success);
        result.put("msg", success ? "添加成功" : "添加失败");
        return result;
    }

    // 更新图书需要管理员权限
    @RequireAdmin
    @PutMapping("/update")
    public Map<String, Object> updateBook(@RequestBody Book book) {
        Map<String, Object> result = new HashMap<>();
        boolean success = bookService.updateBook(book);
        result.put("success", success);
        result.put("msg", success ? "更新成功" : "更新失败");
        return result;
    }

    // 删除图书需要管理员权限
    @RequireAdmin
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteBook(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = bookService.deleteBook(id);
            result.put("success", success);
            result.put("msg", success ? "删除成功" : "删除失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    // 获取下一个可用图书编号（管理员操作）
    @RequireAdmin
    @GetMapping("/nextNumber")
    public Map<String, Object> getNextBookNumber(@RequestParam String category) {
        Map<String, Object> result = new HashMap<>();
        String nextNumber = bookService.getNextBookNumber(category);
        result.put("success", true);
        result.put("data", nextNumber);
        return result;
    }

    // 新书上架榜
    @GetMapping("/new-rank")
    public Map<String, Object> getNewBooksRank() {
        Map<String, Object> result = new HashMap<>();
        List<Book> books = bookService.getNewBooksRank();
        result.put("success", true);
        result.put("data", books);
        return result;
    }

    // 分类榜单
    @GetMapping("/category-rank")
    public Map<String, Object> getCategoryRank() {
        Map<String, Object> result = new HashMap<>();
        List<Book> books = bookService.getCategoryRank();
        result.put("success", true);
        result.put("data", books);
        return result;
    }
}
