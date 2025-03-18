package com.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dto.RoleDTO;
import com.user.entiry.Role;
import com.user.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	public RoleDTO getRoleById(int roleId) {
		Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
		return modelMapper.map(role, RoleDTO.class);
	}

	// Get all RoleDTOs
	public List<RoleDTO> getAllRoles() {
		List<Role> roles = roleRepository.findAll();
		return roles.stream().map(role -> modelMapper.map(role, RoleDTO.class)) // Mapping Role to RoleDTO
				.collect(Collectors.toList());
	}

	public RoleDTO createRole(RoleDTO roleDTO) {
		Role role = modelMapper.map(roleDTO, Role.class);
		Role savedRole = roleRepository.save(role);
		return modelMapper.map(savedRole, RoleDTO.class);
	}

	// Update an existing role
	public RoleDTO updateRole(int roleId, RoleDTO roleDTO) {
		Role existingRole = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
		modelMapper.map(roleDTO, existingRole);
		Role updatedRole = roleRepository.save(existingRole);
		return modelMapper.map(updatedRole, RoleDTO.class);
	}

	public void deleteRole(int roleId) {
		if (!roleRepository.existsById(roleId)) {
			throw new RuntimeException("Role not found");
		}
		roleRepository.deleteById(roleId);
	}
}
