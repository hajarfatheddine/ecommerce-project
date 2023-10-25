package com.ecom.orderservice.service;

import com.ecom.orderservice.dto.InventoryResponse;
import com.ecom.orderservice.dto.OrderLineItemDto;
import com.ecom.orderservice.dto.OrderRequest;
import com.ecom.orderservice.model.Order;
import com.ecom.orderservice.model.OrderLineItem;
import com.ecom.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItemList = orderRequest.getOrderItemDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        order.setOrderLineItemList(orderLineItemList);

        List<String> skuCodes = order.getOrderLineItemList().stream().map(OrderLineItem::getSkuCode).collect(Collectors.toList())

                ;
        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);


        if (allProductsInStock) {
            orderRepository.save(order);
        }
        else {
            throw new IllegalArgumentException("Product is not in stock, Please try later");
        }
    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        return orderLineItem;
    }

}
