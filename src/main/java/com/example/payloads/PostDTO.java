package com.example.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entity.Category;
import com.example.entity.Comment;
import com.example.entity.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDTO {
	
	private long id;

	private String postTitle;

	private String postContent;

	private String imageName;

	private Date postedDate;
	
	private CategoryDTO category;
	
	private UserDTO user;
	
	//private List<Comment> comments = new ArrayList<>();
}
