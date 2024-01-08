package com.devandre.swiftstay.persistence.repository;

import com.devandre.swiftstay.persistence.models.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, UUID> {

    List<BookedRoom> findByRoomId(UUID roomId);

    Optional<BookedRoom> findByBookingConfirmationCode(String bookingConfirmationCode);

    List<BookedRoom> findByGuestEmail(String guestEmail);
}