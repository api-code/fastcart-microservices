package com.easyshopping.inventoryservice.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.easyshopping.inventoryservice.dto.InventoryResponse;
import com.easyshopping.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fastCart/inventory")
@RequiredArgsConstructor

@Component
public class InventoryController {

    private final InventoryService inventoryService;
   
   /*
   //problem -- in this method we are taking a single path variable for multiple request it will make
   //multiples calls
    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode){

        return inventoryService.isInStock(skuCode);
    }*/
    
    //to solve above proble we will creating a list which contains all sku code as a parameter
    //and we will pass a list
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        System.out.println("Received inventory check request for skuCode: {}"+ skuCode);
        return inventoryService.isInStock(skuCode);
    }
}


