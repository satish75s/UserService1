package com.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dto.DepartmentDTO;
import com.user.dto.RoleDTO;
import com.user.entiry.Department;
import com.user.entiry.Role;
import com.user.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper

    
 // Get all DepartmentDTOs
 	public List<DepartmentDTO> getAllDepartments() {
 		List<Department> departments = departmentRepository.findAll();
 		return departments.stream().map(department -> modelMapper.map(department, DepartmentDTO.class)) // Mapping Role to RoleDTO
 				.collect(Collectors.toList());
 	}
    
    public DepartmentDTO getDepartmentById(int departmentId) {  
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return modelMapper.map(department, DepartmentDTO.class);
    }

    // POST: Create a new department from DepartmentDTO
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        // Convert DepartmentDTO to Department entity
        Department department = modelMapper.map(departmentDTO, Department.class);

        // Save the department entity
        Department savedDepartment = departmentRepository.save(department);

        // Return the saved department as a DepartmentDTO
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    // PUT: Update an existing department and return the updated department as DepartmentDTO
    public DepartmentDTO updateDepartment(int departmentId, DepartmentDTO departmentDTO) {
        // Fetch the existing department entity
        Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Map updated fields from DepartmentDTO to existing department entity
        modelMapper.map(departmentDTO, existingDepartment);

        // Save the updated department entity
        Department updatedDepartment = departmentRepository.save(existingDepartment);

        // Return the updated department as DepartmentDTO
        return modelMapper.map(updatedDepartment, DepartmentDTO.class);
    }

    // DELETE: Delete a department by ID
    public void deleteDepartment(int departmentId) {
        // Ensure the department exists before deletion
        if (!departmentRepository.existsById(departmentId)) {
            throw new RuntimeException("Department not found");
        }
        // Delete the department entity
        departmentRepository.deleteById(departmentId);
    }
}
