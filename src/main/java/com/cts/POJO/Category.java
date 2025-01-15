package com.cts.POJO;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
//@DynamicUpdate is used when we have entities with a large number of columns and you expect that only a few columns will be modified frequently.
//Only the modified columns will be updated in the database.
@DynamicUpdate
//only the non-null fields are inserted into the database during an insert operation, improving efficiency.
@DynamicInsert
@Table(name="category")
public class Category {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;

}
