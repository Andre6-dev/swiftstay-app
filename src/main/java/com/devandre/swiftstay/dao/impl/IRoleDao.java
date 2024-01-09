package com.devandre.swiftstay.dao.impl;

import com.devandre.swiftstay.dao.RoleDao;
import com.devandre.swiftstay.persistence.models.Role;
import com.devandre.swiftstay.persistence.models.User;
import com.devandre.swiftstay.persistence.models.enums.ERole;
import com.devandre.swiftstay.persistence.repository.RoleRepository;
import com.devandre.swiftstay.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * andre on 8/01/2024
 */
@Repository("roleDao")
@RequiredArgsConstructor
public class IRoleDao implements RoleDao {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public User removeUserFromRole(Long roleId, UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role>  role = roleRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())){
            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
            return user.get();
        }
        throw new RuntimeException("User not found in role");
    }

    @Override
    public User assignRoleToUser(Long roleId, UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if (user.isPresent() && user.get().getRoles().contains(role.get())){
            throw new RuntimeException(
                    user.get().getFirstName()+ " is already assigned to the" + role.get().getName()+ " role");
        }
        if (role.isPresent()){
            role.get().assignRoleToUser(user.get());
            roleRepository.save(role.get());
        }
        return user.get();
    }

    @Override
    public Role removeAllUsersFromRole(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()){
            role.get().removeAllUsersFromRole();
            roleRepository.save(role.get());
            return role.get();
        }
        throw new RuntimeException("Role not found");
    }
}
