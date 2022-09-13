package com.example.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {

	String resourcename;
	
	String fieldname;
	
	Long field;

	public ResourceNotFoundException(String resourcename, String fieldname, Long field) {
		super(String.format("%s not found with %s : %l",resourcename,fieldname,field));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.field = field;
	}
	
	
	
}
