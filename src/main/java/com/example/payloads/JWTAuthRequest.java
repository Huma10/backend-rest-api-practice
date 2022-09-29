package com.example.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTAuthRequest {

	private String username;
	private String password;
}
