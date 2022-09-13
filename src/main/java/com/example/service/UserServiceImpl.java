package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.exception.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.payloads.UserDTO;
import com.example.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO register(UserDTO userDTO) {
		// covert dto to user
		User user = dtoToUser(userDTO);
		// call save() method: save data in user entity only
		User savedUser = userRepository.save(user);
		//now convert user entoity to dto to pass data
		return userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		
		User updatedUser = userRepository.save(user);		
		
		return userToDTO(updatedUser);
	}

	@Override
	public UserDTO findUserById(Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
		//pass it in dto form
		
		return userToDTO(user);
	}

	@Override
	public List<UserDTO> findAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
		List<UserDTO> usersDTO =  users.stream().map(user->userToDTO(user)).collect(Collectors.toList());
		return usersDTO;
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
		userRepository.delete(user);
	}
	
	// convert dto to user entity
	private User dtoToUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		/*
		 * user.setId(userDTO.getId()); user.setName(userDTO.getName());
		 * user.setEmail(userDTO.getEmail()); user.setPassword(userDTO.getPassword());
		 * user.setAbout(userDTO.getAbout());
		 */
		
		return user;
	}
	
	
	// convert user to userDTO
	private UserDTO userToDTO(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//		userDTO.setId(user.getId());
//		userDTO.setName(user.getName());
//		userDTO.setEmail(user.getEmail());
//		userDTO.setPassword(user.getPassword());
//		userDTO.setAbout(user.getAbout());
		return userDTO;
	}
	
	

}
