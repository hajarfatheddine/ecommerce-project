package com.ecom.orderservice.dtos.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  OrderLineItemDto {
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
