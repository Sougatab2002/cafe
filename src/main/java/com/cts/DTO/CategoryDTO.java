package com.cts.DTO;
import jakarta.persistence.NamedQuery;
import lombok.Data;

@NamedQuery(name = "Category.getAllCategory" , query = "select c from Category c")
@Data
public class CategoryDTO {
	
	private Integer id;
	
	
	private String name;

}

