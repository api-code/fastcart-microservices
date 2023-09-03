package com.easyshopping.inventoryservice.util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.easyshopping.inventoryservice.model.Inventory;
import com.easyshopping.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final InventoryRepository inventoryRepository;
    @Override
    public void run(String... args) throws Exception {
        Inventory inventory = new Inventory();
        inventory.setSkuCode("macbook");
        inventory.setQuantity(100);

        Inventory inventory1 = new Inventory();
        inventory1.setSkuCode("s21");
        inventory1.setQuantity(10);

        inventoryRepository.save(inventory);
        inventoryRepository.save(inventory1);
    }
}