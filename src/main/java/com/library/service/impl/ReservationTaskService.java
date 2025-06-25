package com.library.service.impl;

import com.library.dao.ReservationMapper;
import com.library.dao.BookMapper;
import com.library.entity.Reservation;
import com.library.entity.Book;
import com.library.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationTaskService {
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private MessageService messageService;

    /**
     * 每天凌晨3点检查预约到书提醒
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void checkReservationNotify() {
        List<Reservation> all = reservationMapper.getAllWaitingReservations();
        Map<Integer, List<Reservation>> bookResMap = new HashMap<>();
        for (Reservation r : all) {
            bookResMap.computeIfAbsent(r.getBookId(), k -> new ArrayList<>()).add(r);
        }
        for (Map.Entry<Integer, List<Reservation>> entry : bookResMap.entrySet()) {
            Integer bookId = entry.getKey();
            Book book = bookMapper.getBookById(bookId);
            if (book != null && book.getStock() > 0) {
                Reservation first = entry.getValue().get(0);
                String msg = String.format("您预约的《%s》已可借阅，请及时借阅。", book.getName());
                messageService.sendMessage(first.getUserId(), msg, "SYSTEM");
                reservationMapper.updateReservationStatus(first.getId(), "NOTIFIED");
            }
        }
    }

    /**
     * 检查指定用户的预约到书提醒（用于登录时实时提醒）
     */
    public void checkReservationNotifyForUser(Integer userId) {
        List<Reservation> all = reservationMapper.getAllWaitingReservationsByUserId(userId);
        Map<Integer, List<Reservation>> bookResMap = new HashMap<>();
        for (Reservation r : all) {
            bookResMap.computeIfAbsent(r.getBookId(), k -> new ArrayList<>()).add(r);
        }
        for (Map.Entry<Integer, List<Reservation>> entry : bookResMap.entrySet()) {
            Integer bookId = entry.getKey();
            Book book = bookMapper.getBookById(bookId);
            if (book != null && book.getStock() > 0) {
                Reservation first = entry.getValue().get(0);
                if (first.getUserId().equals(userId)) {
                    String content = String.format("您预约的《%s》已到馆，请在24小时内前往借阅。", book.getName());
                    if (!messageService.existsUnreadMessage(userId, content)) {
                        messageService.sendMessage(userId, content, "SYSTEM");
                    }
                    reservationMapper.updateReservationStatus(first.getId(), "NOTIFIED");
                }
            }
        }
    }
} 