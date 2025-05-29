package com.library.service;

import com.library.entity.Book;
import java.util.List;

public interface BookService {
    // 添加图书
    boolean addBook(Book book);

    // 删除图书
    boolean deleteBook(Integer id);

    // 更新图书信息
    boolean updateBook(Book book);

    // 根据ID查询图书
    Book getBookById(Integer id);

    // 查询所有图书
    List<Book> getAllBooks();

    // 搜索图书
    List<Book> searchBooks(String keyword);

    // 更新库存
    boolean updateStock(Integer id, Integer stock);
}
