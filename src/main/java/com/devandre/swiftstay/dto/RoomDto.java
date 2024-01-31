package com.devandre.swiftstay.dto;

import com.devandre.swiftstay.persistence.models.enums.ERoomType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.devandre.swiftstay.persistence.models.Room}
 */
public record RoomDto(
        @NotNull String name,
        @NotNull ERoomType roomType,
        @NotNull BigDecimal roomPrice,
        @NotNull String slug,
        @NotNull String roomDescription,
        @NotNull Integer numberOfBeds,
        @NotNull String roomDimension,
        @NotNull Boolean isBooked,
        @NotNull Boolean isFeatured,
                      String photoUrl
) implements Serializable {
}