package com.cts.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.DTO.CategoryDTO;
import com.cts.POJO.Category;

public interface CategoryService {

	ResponseEntity<String> addNewCategory(CategoryDTO categoryDTO);
	
	//ResponseEntity<List<Category>> getAllCategory(String Value);
	List<Category> getAllCategory();
	
	ResponseEntity<String> updateCategory(CategoryDTO categoryDTO);
}

