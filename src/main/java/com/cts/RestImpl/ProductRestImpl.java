package com.cts.RestImpl;

import java.util.List;

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

import com.cts.DTO.ProductDTO;
import com.cts.Service.ProductService;
import com.cts.Constants.CafeConstants;
import com.cts.POJO.Product;
import com.cts.Utils.CafeUtils;

@RestController
@RequestMapping(path="/product")
public class ProductRestImpl {

	@Autowired
	ProductService productService;
	
		@PostMapping(path = "/add")
	    public ResponseEntity<String> addNewProduct(@RequestBody ProductDTO productDTO) {
	        try {
	            return productService.addNewProduct( productDTO);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	@GetMapping(path="/get")
	 public List<Product> getAllProducts(){
		 return productService.getAllProducts();
	 }

	@PutMapping(path="/update")
	public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO) {
		try {
			
			return productService.updateProduct(productDTO);
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 
	@DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        try {
            return productService.deleteProduct(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
	 
}
