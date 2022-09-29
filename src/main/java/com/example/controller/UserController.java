package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payloads.APIResponse;
import com.example.payloads.UserDTO;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// add user
	@PostMapping("/")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO userDTO2 = userService.createNewuser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO2, HttpStatus.OK);
	}

	// update user
	@PutMapping("/{userid}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("userid") Long id) {
		UserDTO userDTO2 = userService.updateUser(userDTO, id);
		return ResponseEntity.ok(userDTO2);
	}

	// get user
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(userService.findAllUsers());
	}

	// get single user
	@GetMapping("/{userid}")
	public ResponseEntity<UserDTO> getAllUsers(@PathVariable("userid") Long id) {
		return ResponseEntity.ok(userService.findUserById(id));
	}
	// ADMIN SHOULD BE ABLE TO DELETE ONLY
	@PreAuthorize("hasRole('ADMIN')")
	// delete user
	@DeleteMapping("/{userid}")
	public ResponseEntity<APIResponse> deleteUser(@PathVariable("userid") Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<APIResponse>(new APIResponse("User Deleted Successfully", true), HttpStatus.OK);
	}

}
