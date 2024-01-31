package com.devandre.swiftstay.dto;

import com.devandre.swiftstay.persistence.models.BookedRoom;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link BookedRoom}
 */
public record BookedRoomDto(@NotNull LocalDate checkInDate,
                            @NotNull LocalDate checkOutDate,
                            @NotNull String guestFullName,
                            @NotNull @Email String guestEmail,
                            @NotNull @Positive int numOfAdults,
                            @NotNull @Positive int numOfChildren,
                            @NotNull @Positive int totalNumOfGuest,
                            @NotNull @NotEmpty String bookingConfirmationCode) implements Serializable {
}