package com.cts.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.Service.ProductService;
import com.cts.Constants.CafeConstants;
import com.cts.Utils.CafeUtils;
import com.cts.POJO.Category;
import com.cts.POJO.Product;
import com.cts.DTO.ProductDTO;

import lombok.extern.slf4j.Slf4j;

import com.cts.JWT.JwtFilter;
import com.cts.Dao.ProductDao;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	ProductDao productDao;
	
	@Autowired
    JwtFilter jwtFilter;
	
	@Override
    public ResponseEntity<String> addNewProduct(ProductDTO productDTO) {
        log.info("Inside addNewProduct", productDTO);
        try {
            if (jwtFilter.isAdmin()) {
            	//gets the product and saves it
                    productDao.save(getProduct(productDTO, false));
                    return CafeUtils.getResponseEntity("Product Added Successfully", HttpStatus.OK);
                }
            else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            
        } 
	}catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
	
	
	
  

    private Product getProduct(ProductDTO productDTO, boolean isUpdate) {
        Product product = new Product();
        Category category = new Category();
        category.setId( productDTO.getCategoryId());
        //whenever we will add a product this is false and the setStatus becomes true
        //this line is for update
        if (isUpdate) {
            product.setId(productDTO.getId());
        } else {
            product.setStatus("true");
        }
        product.setCategory(category); // setting the category object to the product's category
        product.setName( productDTO.getName());
        product.setDescription( productDTO.getDescription());
        product.setPrice( productDTO.getPrice());

        return product;
    }
    
    
    @Override
	 public List<Product> getAllProducts(){
		 return productDao.findAll();
		 
	 }


    @Override
    public ResponseEntity<String> updateProduct(ProductDTO productDTO) {
        try {
            if (jwtFilter.isAdmin()) {
                	//to check if that id actually exist or not
                    Optional<Product> optional = productDao.findById(productDTO.getId());
                    //if product id does not exist then it will return "Product id does not exist" but if it does the it will get product from map and the product is updated
                    if (!optional.isEmpty()) {
                        Product product= getProduct(productDTO, true);
                        product.setStatus(optional.get().getStatus());
                        productDao.save(product);
                        return CafeUtils.getResponseEntity("Product is updated successfully", HttpStatus.OK);

                    }
            else {
                        return CafeUtils.getResponseEntity("Product id doesn't exist", HttpStatus.OK);
                    }

                }
               // return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
             else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Product> optional = productDao.findById(id);
                if (!optional.isEmpty()) {
                    productDao.deleteById(id);
                    return CafeUtils.getResponseEntity("Product is deleted successfully", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity("Product id doesn't exist", HttpStatus.OK);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
	

}
