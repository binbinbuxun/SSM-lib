package com.library.service.impl;

import com.library.dao.BookMapper;
import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean addBook(Book book) {
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
    public boolean updateStock(Integer id, Integer stock) {
        return bookMapper.updateStock(id, stock) > 0;
    }
}
