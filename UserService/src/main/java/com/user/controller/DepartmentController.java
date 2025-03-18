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

import com.user.dto.DepartmentDTO;
import com.user.dto.RoleDTO;
import com.user.entiry.Department;
import com.user.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable int departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(department));
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable int departmentId, @RequestBody DepartmentDTO department) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId, department));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }
}
