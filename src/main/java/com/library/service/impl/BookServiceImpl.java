package com.library.service.impl;

import com.library.dao.BookMapper;
import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean addBook(Book book) {
        // 自动生成图书序号（如CP001、WX002等）
        if (book.getBookNumber() == null || book.getBookNumber().trim().isEmpty()) {
            // 使用分类映射表获取前缀
            String prefix = getPrefixByCategory(book.getCategory());
            String maxNumber = bookMapper.getMaxBookNumberByPrefix(prefix);
            int nextNum = 1;
            if (maxNumber != null && maxNumber.length() > 2) {
                try {
                    nextNum = Integer.parseInt(maxNumber.substring(2)) + 1;
                } catch (NumberFormatException ignored) {}
            }
            book.setBookNumber(String.format("%s%03d", prefix, nextNum));
        }
        return bookMapper.insertBook(book) > 0;
    }

    @Override
    public boolean deleteBook(Integer id) {
        return bookMapper.deleteBook(id) > 0;
    }

    @Override
    public boolean updateBook(Book book) {
        return bookMapper.updateBook(book) > 0;
    }

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookMapper.searchBooks(keyword);
    }

    @Override
    public List<Book> searchBooks(String keyword, String category) {
        return bookMapper.searchBooksByKeywordAndCategory(keyword, category);
    }

    @Override
    public boolean updateStock(Integer id, Integer stock) {
        return bookMapper.updateStock(id, stock) > 0;
    }

    @Override
    public List<Book> getBooksByPage(Integer pageNum, Integer pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return bookMapper.getBooksByPage(offset, pageSize);
    }

    @Override
    public int getTotalBooks() {
        return bookMapper.getTotalBooks();
    }

    @Override
    public String getNextBookNumber(String category) {
        String prefix = getPrefixByCategory(category);
        String maxNumber = bookMapper.getMaxBookNumberByPrefix(prefix);
        int nextNum = 1;
        if (maxNumber != null && maxNumber.length() > 2) {
            try {
                nextNum = Integer.parseInt(maxNumber.substring(2)) + 1;
            } catch (NumberFormatException ignored) {}
        }
        return String.format("%s%03d", prefix, nextNum);
    }

    // 分类到前缀的映射
    private String getPrefixByCategory(String category) {
        if (category == null) return "CP";
        Map<String, String> map = new HashMap<>();
        map.put("计算机/编程", "CP");
        map.put("计算机/算法", "CP");
        map.put("计算机/框架", "CP");
        map.put("计算机/设计", "CP");
        map.put("计算机/人工智能", "CP");
        map.put("计算机/数据库", "CP");
        map.put("文学/小说", "WX");
        map.put("文学/散文", "WX");
        map.put("科幻/小说", "KH");
        map.put("科普/历史", "KP");
        map.put("科普/物理", "KP");
        map.put("历史/政治", "LS");
        map.put("历史/通俗", "LS");
        map.put("经济/教材", "JJ");
        map.put("经济/理财", "JJ");
        map.put("经管/市场营销", "JJ");
        // 可继续补充
        return map.getOrDefault(category, "CP");
    }
}
