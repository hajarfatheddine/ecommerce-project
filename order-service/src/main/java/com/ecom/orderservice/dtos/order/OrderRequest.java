package com.ecom.orderservice.dtos.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @NotEmpty(message = "the list should not be empty.Enter at least one item to order")
    private List<OrderLineItemDto> orderItemDtoList;
}
