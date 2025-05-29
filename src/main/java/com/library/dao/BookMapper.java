package com.library.dao;

import com.library.entity.Book;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BookMapper {
    // 添加图书
    int insertBook(Book book);

    // 删除图书
    int deleteBook(Integer id);

    // 更新图书信息
    int updateBook(Book book);

    // 根据ID查询图书
    Book getBookById(Integer id);

    // 查询所有图书
    List<Book> getAllBooks();

    // 根据条件搜索图书
    List<Book> searchBooks(@Param("keyword") String keyword);

    // 更新图书库存
    int updateStock(@Param("id") Integer id, @Param("stock") Integer stock);
}
