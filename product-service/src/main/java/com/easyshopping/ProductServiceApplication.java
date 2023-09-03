package com.easyshopping;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//import com.controller.ProductController;

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan("com.easyshopping")
@EnableMongoRepositories("com.easyshopping.repository")
//@EnableEurekaClient
//@EnableDiscoveryClient
public class ProductServiceApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
		System.out.println("hello product");
	}

}
