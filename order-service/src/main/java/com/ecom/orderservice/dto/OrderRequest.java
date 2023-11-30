package com.ecom.orderservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private List<OrderLineItemDto> orderItemDtoList;
}
