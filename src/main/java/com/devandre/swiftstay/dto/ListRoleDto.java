package com.devandre.swiftstay.dto;

import com.devandre.swiftstay.persistence.models.Role;
import com.devandre.swiftstay.persistence.models.enums.ERole;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Role}
 */
public record ListRoleDto(
        Long id,
        ERole name,
        Instant createdAt,
        Instant lastModifiedAt
) implements Serializable {
}