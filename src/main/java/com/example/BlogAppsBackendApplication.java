package com.example;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.config.Constants;
import com.example.entity.Roles;
import com.example.repository.RoleRepository;

@SpringBootApplication
public class BlogAppsBackendApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(BlogAppsBackendApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Roles roles = new Roles();
			roles.setId(Constants.ADMIN_USER);
			roles.setRoleName("ADMIN_USER");
			
			
			Roles roles2 = new Roles();
			roles2.setId(Constants.NORMAL_USER);
			roles2.setRoleName("NORMAL_USER");
			
			List<Roles> allRole = List.of(roles, roles2);
			
			List<Roles> result = roleRepository.saveAll(allRole);
			
			result.forEach(r->{
				System.out.println(r.getRoleName());
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
