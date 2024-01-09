package com.devandre.swiftstay.persistence.repository;

import com.devandre.swiftstay.persistence.models.Role;
import com.devandre.swiftstay.persistence.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

    boolean existsByName(String role);

}