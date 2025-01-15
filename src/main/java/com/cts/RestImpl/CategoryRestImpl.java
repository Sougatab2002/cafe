package com.cts.RestImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.Constants.CafeConstants;
import com.cts.DTO.CategoryDTO;
import com.cts.Service.CategoryService;
import com.cts.Utils.CafeUtils;
import com.cts.POJO.Category;


@RestController
@RequestMapping(path="/category")
public class CategoryRestImpl{

	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping(path="/add")
	public ResponseEntity<String> addNewCategory(@RequestBody CategoryDTO categoryDTO) {
		try{
			return categoryService.addNewCategory(categoryDTO);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	 
	 //getAllCategory
		@GetMapping(path="/get")
		public List<Category> getAllCategory(){
		 return categoryService.getAllCategory();
	 }
	 
	 
	 //updateCategory
	 @PutMapping(path = "/update")
	    public ResponseEntity<String> update(@RequestBody CategoryDTO categoryDTO) {
	        try {
	            return categoryService.updateCategory( categoryDTO);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
}
