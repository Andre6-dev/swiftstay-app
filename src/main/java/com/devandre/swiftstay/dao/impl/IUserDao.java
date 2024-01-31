package com.devandre.swiftstay.dao.impl;

import com.devandre.swiftstay.dao.UserDao;
import com.devandre.swiftstay.persistence.models.User;
import com.devandre.swiftstay.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * andre on 8/01/2024
 */
@Repository("userDao")
@RequiredArgsConstructor
public class IUserDao implements UserDao {

    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
}
