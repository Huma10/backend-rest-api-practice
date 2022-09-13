package com.example.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

	
	private long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
}
