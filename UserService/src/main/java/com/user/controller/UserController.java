package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.UserDTO;
import com.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/usersByPage")
	public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);	
		Page<UserDTO> users = userService.getAllUsers(pageable);
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(userService.updateUser(userId, userDTO));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
