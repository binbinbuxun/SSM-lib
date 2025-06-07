package com.library.service;

import com.library.entity.Notice;
import java.util.List;

public interface NoticeService {
    List<Notice> getAll();
    boolean add(Notice notice);
    boolean update(Notice notice);
    boolean delete(Integer id);
    Notice getById(Integer id);
}

