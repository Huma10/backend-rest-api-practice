package com.example.service;

import java.util.List;

import com.example.payloads.CategoryDTO;

public interface CategoryService {

	CategoryDTO add(CategoryDTO categoryDTO);
	
	CategoryDTO update(CategoryDTO categoryDTO, long id);
	
	CategoryDTO findByCategoryId(long id);
	
	List<CategoryDTO> findAllCategory();
	
	void delete(long id);
}
