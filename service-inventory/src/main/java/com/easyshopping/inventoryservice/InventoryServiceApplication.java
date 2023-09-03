package com.easyshopping.inventoryservice;

import com.easyshopping.inventoryservice.model.Inventory;
import com.easyshopping.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
//@EnableEurekaClient
//@EnableDiscoveryClient

public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
		System.out.println("hello inventory");}
	

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(0);

			Inventory inventory1 = new Inventory();
			inventory.setSkuCode("Nothing");
			inventory.setQuantity(100);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}
