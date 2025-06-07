package com.library.service.impl;

import com.library.dao.NoticeMapper;
import com.library.entity.Notice;
import com.library.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getAll() { return noticeMapper.getAll(); }
    @Override
    public boolean add(Notice notice) { return noticeMapper.insert(notice) > 0; }
    @Override
    public boolean update(Notice notice) { return noticeMapper.update(notice) > 0; }
    @Override
    public boolean delete(Integer id) { return noticeMapper.deleteById(id) > 0; }
    @Override
    public Notice getById(Integer id) { return noticeMapper.getById(id); }
}

