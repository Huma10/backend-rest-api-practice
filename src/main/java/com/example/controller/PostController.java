package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payloads.PostDTO;
import com.example.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable("userId") long userId,
			@PathVariable("categoryId") long categoryId) {
		PostDTO postDTO2 = postService.add(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(postDTO2, HttpStatus.CREATED);
	}

	// get post by user id
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(
			@PathVariable("userId") long userId) {
		List<PostDTO> listofPost = postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(listofPost, HttpStatus.OK);

	}

	// get post by category id
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(
			@PathVariable("categoryId") long categoryId) {
		List<PostDTO> listofPost = postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(listofPost, HttpStatus.OK);

	}
	// get all post 
	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		List<PostDTO> list = postService.findAllPosts();
		return new ResponseEntity<List<PostDTO>>(list,HttpStatus.OK);
	}
	
	// get post by id
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("id") long postId){
		PostDTO postDTO = postService.findById(postId);
		return new ResponseEntity<PostDTO>(postDTO,HttpStatus.OK);
	}
	
}
