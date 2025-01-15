package com.cts.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;


import com.cts.POJO.Product;
import com.cts.DTO.ProductDTO;

public interface ProductService {

	ResponseEntity<String> addNewProduct(ProductDTO productDTO);
	
	List<Product> getAllProducts();
	
	ResponseEntity<String> updateProduct(ProductDTO productDTO);
	
	ResponseEntity<String> deleteProduct(Integer id);
}
