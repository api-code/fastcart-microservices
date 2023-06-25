package com.easyshopping.orderservice.controller;


import com.easyshopping.orderservice.dto.OrderRequest;
import com.easyshopping.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeorder(@RequestBody OrderRequest orderRequest){
        try{
            orderService.placeOrder(orderRequest);
            System.out.println(orderRequest.getCache());
            return "Order placed successfully";
        }
        catch (Exception e){

            return "Order not placed successfully";
        }

    }
}
