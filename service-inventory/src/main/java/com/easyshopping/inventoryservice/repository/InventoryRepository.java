package com.easyshopping.inventoryservice.repository;

import com.easyshopping.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
@Repository
@EnableJpaRepositories
public interface InventoryRepository extends JpaRepository<Inventory,Long>{
    Optional<Inventory> findBySkuCode(String skuCode);
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
    Optional<Inventory> findBySkuCode();
}
*/


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    //it will return a list of inventory object
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}