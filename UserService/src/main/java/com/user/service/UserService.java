package com.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.user.dto.UserDTO;
import com.user.entiry.User;
import com.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	
	public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserDTO> userDTOPage = userPage.map(user -> modelMapper.map(user, UserDTO.class));
        return userDTOPage;
    }
	
	public List<UserDTO> getAllUsers() {
 		List<User> users = userRepository.findAll();
 		return users.stream().map(department -> modelMapper.map(department, UserDTO.class)) 
 				.collect(Collectors.toList());
 	}

	public UserDTO getUserById(String userId) {
		User userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		return modelMapper.map(userEntity, UserDTO.class);
	}

	public UserDTO createUser(UserDTO userDTO) {
		User userEntity = modelMapper.map(userDTO, User.class);
		User savedUser = userRepository.save(userEntity);
		return modelMapper.map(savedUser, UserDTO.class);
	}

	public UserDTO updateUser(String userId, UserDTO userDTO) {
	    User existingUser = userRepository.findById(userId)
	        .orElseThrow(() -> new RuntimeException("User not found"));
	    modelMapper.map(userDTO, existingUser);    
	    User updatedUser = userRepository.save(existingUser);
	    return modelMapper.map(updatedUser, UserDTO.class);
	}

	public void deleteUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		userRepository.delete(user);
	}
}
