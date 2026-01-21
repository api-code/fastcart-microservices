package com.easyshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.easyshopping.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product,String>{

}
