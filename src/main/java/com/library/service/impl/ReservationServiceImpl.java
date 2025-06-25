package com.library.service.impl;

import com.library.dao.ReservationMapper;
import com.library.entity.Reservation;
import com.library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public boolean addReservation(Integer userId, Integer bookId) {
        return reservationMapper.addReservation(userId, bookId) > 0;
    }

    @Override
    public boolean cancelReservation(Integer userId, Integer bookId) {
        return reservationMapper.cancelReservation(userId, bookId) > 0;
    }

    @Override
    public List<Reservation> getReservationsByUserId(Integer userId) {
        return reservationMapper.getReservationsByUserId(userId);
    }

    @Override
    public List<Reservation> getReservationsByBookId(Integer bookId) {
        return reservationMapper.getReservationsByBookId(bookId);
    }

    @Override
    public Reservation getReservation(Integer userId, Integer bookId) {
        return reservationMapper.getReservation(userId, bookId);
    }
} 