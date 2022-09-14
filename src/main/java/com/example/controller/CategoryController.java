package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payloads.APIResponse;
import com.example.payloads.CategoryDTO;
import com.example.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO categoryDTO2 = categoryService.add(categoryDTO);
		return new ResponseEntity<CategoryDTO>(categoryDTO2,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO
			, @PathVariable("categoryId") long id){
		CategoryDTO categoryDTO2 = categoryService.update(categoryDTO, id);
		return new ResponseEntity<CategoryDTO>(categoryDTO2,HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<APIResponse> deleteCategory(@PathVariable("categoryId") long id) {
		categoryService.delete(id);
		return new ResponseEntity<APIResponse>(new APIResponse("category is deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable("categoryId") long id) {
		CategoryDTO categoryDTO = categoryService.findByCategoryId(id);
		return new ResponseEntity<CategoryDTO>(categoryDTO,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> findAllCategory(){
		List<CategoryDTO> list =  categoryService.findAllCategory();
		return ResponseEntity.ok(list);
	}
}
