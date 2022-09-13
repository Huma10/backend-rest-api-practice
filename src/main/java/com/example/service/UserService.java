package com.example.service;


import java.util.List;

import com.example.payloads.UserDTO;

public interface UserService {

	UserDTO register(UserDTO user);
	
	UserDTO updateUser(UserDTO user,Long userId);
	
	UserDTO findUserById(Long userId);
	
	List<UserDTO> findAllUsers();
	
	void deleteUser(Long userId);
}
