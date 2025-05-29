package com.library.controller;

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

    @PostMapping("/add")
    public Map<String, Object> addBook(@RequestBody Book book) {
        Map<String, Object> result = new HashMap<>();
        boolean success = bookService.addBook(book);
        result.put("success", success);
        result.put("msg", success ? "添加成功" : "添加失败");
        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteBook(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        boolean success = bookService.deleteBook(id);
        result.put("success", success);
        result.put("msg", success ? "删除成功" : "删除失败");
        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> updateBook(@RequestBody Book book) {
        Map<String, Object> result = new HashMap<>();
        boolean success = bookService.updateBook(book);
        result.put("success", success);
        result.put("msg", success ? "更新成功" : "更新失败");
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getBook(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        Book book = bookService.getBookById(id);
        result.put("success", book != null);
        result.put("data", book);
        result.put("msg", book != null ? "查询成功" : "图书不存在");
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getAllBooks() {
        Map<String, Object> result = new HashMap<>();
        List<Book> books = bookService.getAllBooks();
        result.put("success", true);
        result.put("data", books);
        result.put("msg", "查询成功");
        return result;
    }

    @GetMapping("/search")
    public Map<String, Object> searchBooks(@RequestParam String keyword) {
        Map<String, Object> result = new HashMap<>();
        List<Book> books = bookService.searchBooks(keyword);
        result.put("success", true);
        result.put("data", books);
        result.put("msg", "查询成功");
        return result;
    }

    @PutMapping("/{id}/stock")
    public Map<String, Object> updateStock(@PathVariable Integer id, @RequestParam Integer stock) {
        Map<String, Object> result = new HashMap<>();
        boolean success = bookService.updateStock(id, stock);
        result.put("success", success);
        result.put("msg", success ? "库存更新成功" : "库存更新失败");
        return result;
    }
}
