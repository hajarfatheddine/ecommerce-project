package com.ecom.inventoryservice.dtos.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Data
public class ResponseBody<T> {
    private String code;
    private String description;
    private T result;
}
