package com.devandre.swiftstay.dao.impl;

import com.devandre.swiftstay.dao.BookingDao;
import com.devandre.swiftstay.persistence.models.BookedRoom;
import com.devandre.swiftstay.persistence.repository.BookedRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * andre on 9/01/2024
 */
@Repository("bookingDao")
@RequiredArgsConstructor
public class IBookingDao implements BookingDao {

    private final BookedRoomRepository bookedRoomRepository;

    @Override
    public List<BookedRoom> selectAllBookings() {
        return bookedRoomRepository.findAll();
    }

    @Override
    public List<BookedRoom> selectBookingsByUserEmail(String email) {
        return bookedRoomRepository.findByGuestEmail(email);
    }

    @Override
    public List<BookedRoom> selectBookingsByRoomId(UUID roomId) {
        return bookedRoomRepository.findByRoomId(roomId);
    }

    @Override
    public Optional<BookedRoom> selectByBookingConfirmationCode(String bookingConfirmationCode) {
        return bookedRoomRepository.findByBookingConfirmationCode(bookingConfirmationCode);
    }

    @Override
    public BookedRoom insertBooking(BookedRoom bookedRoom) {
        return bookedRoomRepository.save(bookedRoom);
    }

    @Override
    public void deleteBookingById(UUID id) {
        bookedRoomRepository.deleteById(id);
    }
}
