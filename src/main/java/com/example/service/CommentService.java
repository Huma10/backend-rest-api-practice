package com.example.service;

import com.example.payloads.CommentDTO;

public interface CommentService {

	CommentDTO add(CommentDTO commentDTO, long postId, long userid);
	
	void delete(long commentId);
}
