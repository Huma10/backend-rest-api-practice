package com.example.service;

import java.util.List;

import com.example.payloads.PostDTO;
import com.example.payloads.PostResponse;

public interface PostService {

	PostDTO add(PostDTO postDTO, long userId, long categoryId);
	
	PostDTO update(PostDTO postDTO,long id);
	
	PostResponse findAllPosts(Integer pageNumber,Integer pageSize, String sortBy, String sortDir);
	
	PostDTO findById(long id);
	
	void delete(long id);
	
	// get all posts by category
	List<PostDTO> getPostByCategory(long categoryId);
	
	// get all posts by user
	List<PostDTO> getPostByUser(long userId);
	
	//search post	
	List<PostDTO> searchPosts(String keyword);
	
}
