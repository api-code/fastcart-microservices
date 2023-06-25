package com.easyshopping.model;

//import com.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(value="product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
	@Id  //ID annotation from spring data to specify that this is unique identifier
	private String id;
	private String name;
	private String description;
	private BigDecimal price;

}
