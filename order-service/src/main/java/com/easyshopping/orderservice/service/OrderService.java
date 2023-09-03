package com.easyshopping.orderservice.service;

import com.easyshopping.orderservice.dto.InventoryResponse;
import com.easyshopping.orderservice.dto.OrderLineItemsDto;
import com.easyshopping.orderservice.dto.OrderRequest;
import com.easyshopping.orderservice.model.Order;
import com.easyshopping.orderservice.model.OrderLineItems;
import com.easyshopping.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    //injecting webClient into order service class
    private final WebClient.Builder webClientBuilder;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);
        //collecting all sku code from order object
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        System.out.println("calling inverntory"+skuCodes);
        //making call for inventroy service and place order if product is in stock
        InventoryResponse[] InventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()//to retrive the response
                .bodyToMono(InventoryResponse[].class)//type of response -mono is datatype of reactive framework
                .block();//web client will makre synchornouse call
        System.out.println(InventoryResponseArray +"inverntory");
        //here we got inventory response array and we are converting it to a strea and we are calling the all match method
        // from java8 it will check whether the isInStock variable is true inside the array or not
        //if all elements inside the inventroy response list contains isInStock as true then it will return as true
        //even if one of them is false we will get the all products in stack as false
        boolean allProductsInStcok = Arrays.stream(InventoryResponseArray).allMatch(InventoryResponse::isInStock);

        //if the product is in stcok then placed order else throw exception
        if (allProductsInStcok) {
            orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("product is not in stock please try again later");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;

    }
}
