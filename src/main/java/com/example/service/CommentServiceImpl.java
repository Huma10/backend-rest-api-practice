package com.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.payloads.CommentDTO;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public CommentDTO add(CommentDTO commentDTO, long postId, long userid) {
		
		Post post = postRepository.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post ", "Post Id", postId));
		User user = userRepository.findById(userid)
				.orElseThrow(()->new ResourceNotFoundException("User ", "User Id", userid));
		// change dto to entity
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = commentRepository.save(comment);
		return modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void delete(long commentId) {
		// TODO Auto-generated method stub
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment ", "COmment Id", commentId));
		commentRepository.delete(comment);
	}

}
