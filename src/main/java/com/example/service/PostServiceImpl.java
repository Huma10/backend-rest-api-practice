package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.payloads.PostDTO;
import com.example.repository.CategoryRepository;
import com.example.repository.PostRepository;
import com.example.repository.UserRepository;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public PostDTO add(PostDTO postDTO,long userId, long categoryId) {
		// TODO Auto-generated method stub
		Post post =  modelMapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setPostedDate(new Date());
		
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ", "user id", userId));
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Catgeory ", "Catgory id", categoryId));
		
		post.setUser(user);
		post.setCategory(category);
		
		Post addedPost = postRepository.save(post);
		
		return modelMapper.map(addedPost, PostDTO.class);
	}

	@Override
	public PostDTO update(PostDTO postDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDTO> findAllPosts() {
		// TODO Auto-generated method stub
		List<Post> list = postRepository.findAll();
		List<PostDTO> postDTOs = list.stream().map((post)->modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public PostDTO findById(long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post ", "Post id", id));
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PostDTO> getPostByCategory(long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		
		List<Post> posts = postRepository.findByCategory(category);
		List<PostDTO> postDTOs = posts.stream().map((post)->modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> getPostByUser(long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User ", "UserId", userId));
		List<Post> posts =  postRepository.findByUser(user);
		
		List<PostDTO> postDTOs = posts.stream().map((post)->modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
