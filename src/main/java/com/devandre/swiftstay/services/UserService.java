package com.devandre.swiftstay.services;

import com.devandre.swiftstay.dao.UserDao;
import com.devandre.swiftstay.dto.ListUserDto;
import com.devandre.swiftstay.dto.mappers.ListUserMapper;
import com.devandre.swiftstay.exception.common.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * andre on 8/01/2024
 */
@Slf4j(topic = "UserService")
@Service
public class UserService {

    private final UserDao userDao;
    private final ListUserMapper listUserMapper;

    public UserService(
            @Qualifier("userDao") UserDao userDao, ListUserMapper listUserMapper) {
        this.userDao = userDao;
        this.listUserMapper = listUserMapper;
    }

    public List<ListUserDto> getAllUsers() {
        log.info("Getting all users");
        return userDao.findAllUsers()
                .stream()
                .map(listUserMapper::toDto)
                .toList();
    }

    public ListUserDto getUserByEmail(String email) {
        log.info("Getting user with email [{}]", email);
        return this.userDao.findUserByEmail(email)
                .map(listUserMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with email [%s] not found".formatted(email)));
    }

    public void deleteUserByEmail(String email) {
        log.info("Deleting user with email [{}]", email);

        if (this.userDao.findUserByEmail(email).isEmpty()) {
            throw new ResourceNotFoundException("User with email [%s] not found".formatted(email));
        }

        this.userDao.deleteUserByEmail(email);
    }

}
