package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.repository.UserRepository;
import com.example.service.UserService;

@SpringBootTest
class BlogAppsBackendApplicationTests {

	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest() {
	 String name = userService.getClass().getName();
	 String pckname = userService.getClass().getPackageName();
	 System.out.println("Class Name: "+name+" package name: "+pckname);
	}

}
