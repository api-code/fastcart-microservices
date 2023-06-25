package com.easyshopping.service;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easyshopping.dto.ProductRequest;
import com.easyshopping.dto.ProductResponse;
import com.easyshopping.model.Product;
import com.easyshopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //at the compile time it will create the all required constructor
@Slf4j //using this we can create logs
public class ProductService {
	//to save the instance into the database so we need to access the product repository that why we inject it
	private final ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) {
		System.out.println("in service layer "+productRequest);
		//mapping the product request to the product model also this is the instance of the product
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();

		//to save the instance into the database 
		productRepository.save(product);
		log.info("Product {} is saved", product.getId()); //{} using this placeholder it will get the id of product and it will replace with the placeholder
	}

	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		
		return products.stream().map(this::mapToProductresponse).toList();
	}

	private ProductResponse mapToProductresponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
