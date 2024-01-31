package com.devandre.swiftstay.dao;

import com.devandre.swiftstay.persistence.models.BookedRoom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingDao {

    List<BookedRoom> selectAllBookings();

    List<BookedRoom> selectBookingsByUserEmail(String email);

    List<BookedRoom> selectBookingsByRoomId(UUID roomId);

    Optional<BookedRoom> selectByBookingConfirmationCode(String bookingConfirmationCode);

    BookedRoom insertBooking(BookedRoom bookedRoom);

    void deleteBookingById(UUID id);
}
