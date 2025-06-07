package com.library.dao;

import com.library.entity.Notice;
import java.util.List;

public interface NoticeMapper {
    List<Notice> getAll();
    int insert(Notice notice);
    int update(Notice notice);
    int deleteById(Integer id);
    Notice getById(Integer id);
}

