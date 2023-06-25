package com.easyshopping.inventoryservice.service;

import com.easyshopping.inventoryservice.dto.InventoryResponse;
import com.easyshopping.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

@Component
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        System.out.println("Checking Inventory");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventory ->InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
    /*
    @Transactional(readOnly = true)
//    @Transactional
    public boolean isInStock(String skucode){
        return inventoryRepository.findBySkuCode().isPresent();
    }*/
}
