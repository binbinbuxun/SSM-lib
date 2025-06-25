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

    // 关键字+分类联合搜索
    List<Book> searchBooksByKeywordAndCategory(@Param("keyword") String keyword, @Param("category") String category);

    // 更新图书库存
    int updateStock(@Param("id") Integer id, @Param("stock") Integer stock);

    // 分页查询图书
    List<Book> getBooksByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 获取图书总数
    int getTotalBooks();

    // 查询某分类下最大的图书序号
    String getMaxBookNumberByPrefix(@Param("prefix") String prefix);

    // 新书上架榜
    List<Book> getNewBooksRank();

    // 分类榜单
    List<Book> getCategoryRank();
}
