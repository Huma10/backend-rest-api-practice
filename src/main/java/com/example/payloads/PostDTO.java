package com.example.payloads;

import java.util.Date;

import com.example.entity.Category;
import com.example.entity.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDTO {

	private String postTitle;

	private String postContent;

	private String imageName;

	private Date postedDate;
	
	private CategoryDTO category;
	
	private UserDTO user;
}
