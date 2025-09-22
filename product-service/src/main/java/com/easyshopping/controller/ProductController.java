package com.easyshopping.controller;

//import com.model.Product;

import com.easyshopping.dto.ProductRequest;
import com.easyshopping.dto.ProductResponse;
import com.easyshopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductController{
	
	@GetMapping("/")
	public String hi() {
		return "hii";
		
	}
	@GetMapping("/hi")
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "home";
	}
	
	
	@Autowired
	private final ProductService productService;
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		try {
			System.out.println(productRequest);
		System.out.println("hiiiiiiiii");
			productService.createProduct(productRequest);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts(){
		return productService.getAllProducts();
	}
	
	
}
