package com.devandre.swiftstay.controllers;

import com.devandre.swiftstay.controllers.response.ResponseHandler;
import com.devandre.swiftstay.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.devandre.swiftstay.utils.Constants.API_BASE_PATH;

/**
 * andre on 8/01/2024
 */
@RestController
@RequestMapping(API_BASE_PATH + "users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseHandler.response(HttpStatus.OK, userService.getAllUsers(), true);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
        return ResponseHandler.response(HttpStatus.OK, userService.getUserByEmail(email), true);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Object> deleteUser(@PathVariable("email") String email) {
        userService.deleteUserByEmail(email);
        return ResponseHandler.response(HttpStatus.OK, "User deleted successfully", true);
    }
}
