package com.devandre.swiftstay.dto;

import com.devandre.swiftstay.persistence.models.BookedRoom;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link BookedRoom}
 */
public record ListBookedRoomDto(
        Instant createdAt,
        UUID id,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        String guestFullName,
        String guestEmail,
        Integer numOfAdults,
        Integer numOfChildren,
        Integer totalNumOfGuest,
        String bookingConfirmationCode
) implements Serializable {
}