package com.ecom.productservice.exceptions;

import com.ecom.productservice.enums.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class BusinessException extends Exception {
    private final MessageCode code;
    private final String description;
}