package com.example.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.config.Constants;
import com.example.payloads.APIResponse;
import com.example.payloads.PostDTO;
import com.example.payloads.PostResponse;
import com.example.service.FileService;
import com.example.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable("userId") long userId,
			@PathVariable("categoryId") long categoryId) {
		PostDTO postDTO2 = postService.add(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(postDTO2, HttpStatus.CREATED);
	}

	// get post by user id
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable("userId") long userId) {
		List<PostDTO> listofPost = postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(listofPost, HttpStatus.OK);

	}

	// get post by category id
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable("categoryId") long categoryId) {
		List<PostDTO> listofPost = postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(listofPost, HttpStatus.OK);

	}

	// get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = Constants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = Constants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = Constants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = Constants.SORT_DIR,required = false) String sortDir
			) {
		PostResponse list = postService.findAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(list, HttpStatus.OK);
	}

	// get post by id
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("id") long postId) {
		PostDTO postDTO = postService.findById(postId);
		return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
	}

	// delete post
	@DeleteMapping("/posts/{id}")
	public APIResponse deletePost(@PathVariable("id") long postId) {
		postService.delete(postId);
		return new APIResponse("Post Deleted Successfully", true);
	}

	// update post
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable("id") long postId) {
		PostDTO postDTO2 = postService.update(postDTO, postId);
		return new ResponseEntity<PostDTO>(postDTO2,HttpStatus.OK);
	}
	
	// searching
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> search(
			@PathVariable("keywords") String keywords){
		List<PostDTO> postDTOs = postService.searchPosts("%"+keywords+"%");
		return new ResponseEntity<List<PostDTO>>(postDTOs,HttpStatus.OK);
	}
	
	// image upload post
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile file,
			@PathVariable("postId") long postId) throws IOException{
		String fileName = fileService.uploadImage(path, file);
		PostDTO postDTO = postService.findById(postId);
		postDTO.setImageName(fileName);
		PostDTO postDTO2 = postService.update(postDTO, postId);
		return new ResponseEntity<PostDTO>(postDTO2,HttpStatus.OK);
	}
	
	// to show image
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName, HttpServletResponse response
			) throws IOException {
		InputStream in = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		org.springframework.util.StreamUtils.copy(in, response.getOutputStream());
	}
}
