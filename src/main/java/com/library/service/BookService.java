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

    // 支持关键字和分类联合搜索
    List<Book> searchBooks(String keyword, String category);

    // 更新库存
    boolean updateStock(Integer id, Integer stock);

    // 分页查询图书
    List<Book> getBooksByPage(Integer pageNum, Integer pageSize);

    // 获取图书总数
    int getTotalBooks();

    // 获取下一个可用的图书序号
    String getNextBookNumber(String category);

    // 新书上架榜
    List<Book> getNewBooksRank();

    // 分类榜单
    List<Book> getCategoryRank();
}
