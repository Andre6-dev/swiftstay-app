package com.devandre.swiftstay.dao;

import com.devandre.swiftstay.persistence.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    List<User> findAllUsers();

    Optional<User> findUserByEmail(String email);

    User createUser(User user);

    void deleteUserByEmail(String email);
}
