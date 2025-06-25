package com.library.dao;

import com.library.entity.Reservation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ReservationMapper {
    int addReservation(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
    int cancelReservation(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
    List<Reservation> getReservationsByUserId(@Param("userId") Integer userId);
    List<Reservation> getReservationsByBookId(@Param("bookId") Integer bookId);
    Reservation getReservation(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
    // 查询所有WAITING状态的预约
    List<Reservation> getAllWaitingReservations();
    // 更新预约状态
    int updateReservationStatus(@Param("id") Integer id, @Param("status") String status);
    List<Reservation> getAllWaitingReservationsByUserId(Integer userId);
} 