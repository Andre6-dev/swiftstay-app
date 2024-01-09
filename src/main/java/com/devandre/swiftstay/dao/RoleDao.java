package com.devandre.swiftstay.dao;

import com.devandre.swiftstay.persistence.models.Role;
import com.devandre.swiftstay.persistence.models.User;
import com.devandre.swiftstay.persistence.models.enums.ERole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleDao {

    List<Role> findAllRoles();

    Optional<Role> findRoleByName(String name);

    Role createRole(Role role);

    void deleteRoleById(Long id);

    User removeUserFromRole(Long roleId, UUID userId);

    User assignRoleToUser(Long roleId, UUID userId);

    Role removeAllUsersFromRole(Long roleId);
}
