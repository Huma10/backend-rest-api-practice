package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.payloads.CategoryDTO;
import com.example.payloads.UserDTO;
import com.example.repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO add(CategoryDTO categoryDTO) {
		//convert dto to entity
		                //source      //destination: it return category object
 		Category category = modelMapper.map(categoryDTO, Category.class);
 		Category addedCategory = categoryRepository.save(category);
 		// convert back to dto
		return modelMapper.map(addedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO update(CategoryDTO categoryDTO, long id) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Category ", "Category Id", id));
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());
		Category updatedCategory = categoryRepository.save(category);
		return modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO findByCategoryId(long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Category ", "Category Id", id));
		return modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> findAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOs =  categories.stream()
				.map(cat->modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return categoryDTOs;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Category ", "Category Id", id));
		categoryRepository.delete(category);
	}
	
	

}
