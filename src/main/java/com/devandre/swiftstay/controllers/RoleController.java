package com.devandre.swiftstay.controllers;

import com.devandre.swiftstay.controllers.response.ResponseHandler;
import com.devandre.swiftstay.dto.RoleDto;
import com.devandre.swiftstay.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{roleName}")
    public ResponseEntity<Object> getRoleByName(@PathVariable("roleName") String roleName) {
        return ResponseHandler.response(HttpStatus.OK, roleService.getRoleByName(roleName), true);
    }

    @PostMapping
    public ResponseEntity<Object> createRole(@Valid @RequestBody RoleDto roleDto) {
        return ResponseHandler.response(HttpStatus.OK, roleService.createRole(roleDto), true);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Object> deleteRole(@PathVariable("roleId") Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseHandler.response(HttpStatus.OK, "Role deleted successfully", true);
    }

    @PostMapping("/remove-user-from-role")
    public ResponseEntity<Object> removeUserFromRole(@RequestParam("roleId") Long roleId, @RequestParam("userId") UUID userId) {
        roleService.removeUserFromRole(roleId, userId);
        return ResponseHandler.response(HttpStatus.OK, "User removed from " + roleId, true);
    }

    @PostMapping("/assign-role-to-user")
    public ResponseEntity<Object> assignRoleToUser(@RequestParam(value = "roleId") Long roleId,
                                                   @RequestParam(value = "userId") UUID userId) {
        roleService.assignRoleToUser(roleId, userId);
        return ResponseHandler.response(HttpStatus.OK, "User assigned to " + roleId, true);
    }

    @PostMapping("/remove-all-users-from-role")
    public ResponseEntity<Object> removeAllUsersFromRole(@RequestParam("roleId") Long roleId) {
        roleService.removeAllUsersFromRole(roleId);
        return ResponseHandler.response(HttpStatus.OK, "All users removed from " + roleId, true);
    }
}
