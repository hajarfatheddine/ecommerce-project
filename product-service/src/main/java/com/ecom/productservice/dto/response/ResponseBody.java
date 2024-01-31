package com.ecom.productservice.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Generated
public class ResponseBody<T> {
    private String code;
    private String message;
    private T result;
}
