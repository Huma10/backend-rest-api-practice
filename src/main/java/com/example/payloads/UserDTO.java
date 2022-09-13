package com.example.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

	
	private long id;
	@NotEmpty
	@Size(min = 4, message = "Name lenght must be more than 4 characters")
	private String name;
	@Email
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String about;
}
