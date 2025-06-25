package com.library.service;

import com.library.entity.Reservation;
import java.util.List;

public interface ReservationService {
    boolean addReservation(Integer userId, Integer bookId);
    boolean cancelReservation(Integer userId, Integer bookId);
    List<Reservation> getReservationsByUserId(Integer userId);
    List<Reservation> getReservationsByBookId(Integer bookId);
    Reservation getReservation(Integer userId, Integer bookId);
} 