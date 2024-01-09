package com.devandre.swiftstay.dto;

import com.devandre.swiftstay.persistence.models.Role;
import com.devandre.swiftstay.persistence.models.enums.ERole;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
public record RoleDto(
        @NotNull(message = "The name can not be null") String name
) implements Serializable {
}