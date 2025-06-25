package com.library.controller;

import com.library.entity.Reservation;
import com.library.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/add")
    public Map<String, Object> addReservation(@RequestBody Map<String, Integer> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer userId = ((com.library.entity.User) userObj).getId();
        Integer bookId = payload.get("bookId");
        boolean success = reservationService.addReservation(userId, bookId);
        result.put("success", success);
        result.put("msg", success ? "预约成功" : "预约失败或已预约");
        return result;
    }

    @PostMapping("/cancel")
    public Map<String, Object> cancelReservation(@RequestBody Map<String, Integer> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer userId = ((com.library.entity.User) userObj).getId();
        Integer bookId = payload.get("bookId");
        boolean success = reservationService.cancelReservation(userId, bookId);
        result.put("success", success);
        result.put("msg", success ? "取消预约成功" : "取消预约失败");
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> getMyReservations(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("msg", "请先登录");
            return result;
        }
        Integer userId = ((com.library.entity.User) userObj).getId();
        List<Reservation> reservations = reservationService.getReservationsByUserId(userId);
        result.put("success", true);
        result.put("data", reservations);
        return result;
    }

    @GetMapping("/book/{bookId}")
    public Map<String, Object> getReservationsByBook(@PathVariable Integer bookId) {
        Map<String, Object> result = new HashMap<>();
        List<Reservation> reservations = reservationService.getReservationsByBookId(bookId);
        result.put("success", true);
        result.put("data", reservations);
        return result;
    }
} 