package com.devandre.swiftstay.dto;

import com.devandre.swiftstay.persistence.models.enums.ERoomType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link com.devandre.swiftstay.persistence.models.Room}
 */
public record ListRoomDto(
        Instant createdAt,
        Instant lastModifiedAt,
        UUID id,
        String name,
        ERoomType roomType,
        BigDecimal roomPrice,

        String roomDescription,

        Integer numberOfBeds,

        String roomDimension,

        Boolean isBooked,

        Boolean isFeatured,
        String photoUrl
) implements Serializable {
}