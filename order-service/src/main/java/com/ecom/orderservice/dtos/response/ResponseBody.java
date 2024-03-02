package com.ecom.orderservice.dtos.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Generated
public class ResponseBody<T> {
    private String code;
    private String description;
    private T result;
}
