package com.cts.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.Constants.CafeConstants;
import com.cts.DTO.CategoryDTO;
import com.cts.Dao.CategoryDao;
import com.cts.JWT.JwtFilter;
import com.cts.Service.CategoryService;
import com.cts.POJO.Category;
import com.cts.Utils.CafeUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

	//We need the object of CategoryDao to store the data
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Override
	public ResponseEntity<String> addNewCategory(CategoryDTO categoryDTO) {
		try {
			if(jwtFilter.isAdmin()) {
					//returns category to store it in database
					categoryDao.save(getCategory( categoryDTO, false));
					return CafeUtils.getResponseEntity("Category Added Successfully",HttpStatus.OK);
				}
			else {
				return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS ,HttpStatus.UNAUTHORIZED);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		 return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	
	 //A flag indicating whether the operation is for adding a new category (false) or updating an existing one (true).
	 //for adding new Category Id is not needed but for updating id is needed
	 private Category getCategory(CategoryDTO categoryDTO, Boolean isUpdate){
	        Category category = new Category();
	        if(isUpdate){
	            category.setId(categoryDTO.getId());
	        }
	        category.setName( categoryDTO.getName());
	        return category;
	    }

	 
	 
	 //getAllCategory
	 @Override
	 public List<Category> getAllCategory(){
		 return categoryDao.findAll();
		 
	 }
	 
	 
	 //updateCategory
	 @Override
	    public ResponseEntity<String> updateCategory(CategoryDTO categoryDTO) {
	        try {
	            if (jwtFilter.isAdmin()) {
	                	//If the findById method finds a record, the Optional will contain the Category object.
	                    Optional<Category> optional = categoryDao.findById(categoryDTO.getId());
	                    //if the id is not empty then it will be updated or else it will return that id doesnot exist
	                    if (!optional.isEmpty()) {
	                    	
	                    //we are passing the true because if it is true then only it will set the id in the category object and will return it and it will be saved in database
	                        categoryDao.save(getCategory(categoryDTO,true));
	                        return CafeUtils.getResponseEntity("Category is updated successfully", HttpStatus.OK);

	                    } else {
	                        return CafeUtils.getResponseEntity("Category id doesn't exist", HttpStatus.OK);
	                    }

//	                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
	            } else {
	                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	
	
}
