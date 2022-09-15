package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payloads.APIResponse;
import com.example.payloads.CommentDTO;
import com.example.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@PostMapping("/post/{postId}/user/{userId}/comments")
	public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO,
			
			@PathVariable("postId") long postId,@PathVariable("userId") long userId){
		CommentDTO commentDTO2 = commentService.add(commentDTO, postId, userId);
		return new ResponseEntity<CommentDTO>(commentDTO2,HttpStatus.CREATED);
	}
	@DeleteMapping("/comment/{id}")
	public APIResponse delete(@PathVariable("id") long commentId){
		commentService.delete(commentId);
		return new APIResponse("Comment Deleted Successfully", true);
	}
}
