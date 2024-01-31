package com.devandre.swiftstay.dto;

import com.devandre.swiftstay.persistence.models.User;
import com.devandre.swiftstay.persistence.models.enums.EAccountStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
public record ListUserDto(
        Instant createdAt,
        UUID id,
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber,
        EAccountStatus accountStatus,
        Date lastLoginDate,
        Integer failedLoginAttempts) implements Serializable {
}