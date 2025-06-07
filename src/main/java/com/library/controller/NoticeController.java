package com.library.controller;

import com.library.entity.Notice;
import com.library.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/list")
    public List<Notice> list() {
        return noticeService.getAll();
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Notice notice) {
        Map<String, Object> res = new HashMap<>();
        notice.setDate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        res.put("success", noticeService.add(notice));
        return res;
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody Notice notice) {
        Map<String, Object> res = new HashMap<>();
        res.put("success", noticeService.update(notice));
        return res;
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Integer id) {
        Map<String, Object> res = new HashMap<>();
        res.put("success", noticeService.delete(id));
        return res;
    }
}

