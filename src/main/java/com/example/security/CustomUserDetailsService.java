package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User", "Email "+username, 0L));
		System.out.println("User "+user.getEmail()+" Password "+user.getPassword());
		return user;
	}

}
