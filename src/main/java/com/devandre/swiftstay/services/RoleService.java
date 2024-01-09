package com.devandre.swiftstay.services;

import com.devandre.swiftstay.dao.RoleDao;
import com.devandre.swiftstay.dto.ListRoleDto;
import com.devandre.swiftstay.dto.mappers.ListRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * andre on 9/01/2024
 */
@Slf4j(topic = "RoleService")
@Service
public class RoleService {

    private final RoleDao roleDao;
    private final ListRoleMapper listRoleMapper;

    public RoleService(
            @Qualifier("roleDao") RoleDao roleDao,
            ListRoleMapper listRoleMapper
    ) {
        this.roleDao = roleDao;
        this.listRoleMapper = listRoleMapper;
    }

    public List<ListRoleDto> getAllRoles() {
        log.info("Getting all roles");
        return roleDao.findAllRoles()
                .stream()
                .map(listRoleMapper::toDto)
                .toList();
    }
}
