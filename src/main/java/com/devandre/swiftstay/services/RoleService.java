package com.devandre.swiftstay.services;

import com.devandre.swiftstay.dao.RoleDao;
import com.devandre.swiftstay.dto.ListRoleDto;
import com.devandre.swiftstay.dto.RoleDto;
import com.devandre.swiftstay.dto.mappers.ListRoleMapper;
import com.devandre.swiftstay.dto.mappers.RoleMapper;
import com.devandre.swiftstay.exception.common.ResourceNotFoundException;
import com.devandre.swiftstay.persistence.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * andre on 9/01/2024
 */
@Slf4j(topic = "RoleService")
@Service
public class RoleService {

    private final RoleDao roleDao;
    private final ListRoleMapper listRoleMapper;
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleService(
            @Qualifier("roleDao") RoleDao roleDao,
            ListRoleMapper listRoleMapper, RoleMapper roleMapper, RoleRepository roleRepository
    ) {
        this.roleDao = roleDao;
        this.listRoleMapper = listRoleMapper;
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    public List<ListRoleDto> getAllRoles() {
        log.info("Getting all roles");
        return roleDao.findAllRoles()
                .stream()
                .map(listRoleMapper::toDto)
                .toList();
    }

    public ListRoleDto getRoleByName(String name) {
        log.info("Getting role with name [{}]", name);
        return this.roleDao.findRoleByName(name)
                .map(listRoleMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Role with name [%s] not found".formatted(name)));
    }

    public RoleDto createRole(RoleDto roleDto) {
        log.info("Creating role with name [{}]", roleDto.name());

        if (roleRepository.existsByName(roleDto.name())) {
            throw new ResourceNotFoundException("Role with name [%s] already exists".formatted(roleDto.name()));
        }

        return roleMapper.toDto(roleDao.createRole(roleMapper.toEntity(roleDto)));
    }

    public void deleteRole(Long roleId) {
        log.info("Deleting role with id [{}]", roleId);
        roleDao.deleteRoleById(roleId);
    }

    public void removeUserFromRole(Long roleId, UUID userId) {
        log.info("Removing user with id [{}] from role with id [{}]", userId, roleId);
        roleDao.removeUserFromRole(roleId, userId);
    }

    public void assignRoleToUser(Long roleId, UUID userId) {
        log.info("Assigning role with id [{}] to user with id [{}]", roleId, userId);
        roleDao.assignRoleToUser(roleId, userId);
    }

    public void removeAllUsersFromRole(Long roleId) {
        log.info("Removing all users from role with id [{}]", roleId);
        roleDao.removeAllUsersFromRole(roleId);
    }

}
