package com.devandre.swiftstay.controllers;

import com.devandre.swiftstay.controllers.response.ResponseHandler;
import com.devandre.swiftstay.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.devandre.swiftstay.utils.Constants.API_BASE_PATH;

/**
 * andre on 9/01/2024
 */
@RestController
@RequestMapping(API_BASE_PATH + "roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        return ResponseHandler.response(HttpStatus.OK, roleService.getAllRoles(), true);
    }

}
