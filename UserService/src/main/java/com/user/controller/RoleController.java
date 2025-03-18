package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.RoleDTO;
import com.user.dto.UserDTO;
import com.user.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
    
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable int roleId) {
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(role));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable int roleId, @RequestBody RoleDTO role) {
        return ResponseEntity.ok(roleService.updateRole(roleId, role));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable int roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }
}
