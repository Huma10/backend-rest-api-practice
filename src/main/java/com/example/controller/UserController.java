package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payloads.UserDTO;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	//add user
	@PostMapping("/")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
		UserDTO userDTO2 = userService.register(userDTO);
		return new ResponseEntity<UserDTO>(userDTO2,HttpStatus.OK);
	}
	
	//update user
	
	
	//get user
	
	
	//delete user
	
}
