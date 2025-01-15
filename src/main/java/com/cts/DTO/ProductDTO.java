package com.cts.DTO;


import com.cts.POJO.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO{
	
    private Integer id;

    private String name;

    private String description;

  
    private Integer price;

  
    private String status;
    
    private Category category;
    
    private Integer categoryId;

}


