package com.ecom.inventoryservice.exceptions;

import com.ecom.inventoryservice.enums.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@Getter
@Generated
@AllArgsConstructor
public class BusinessException extends Exception {
    private final MessageCode code;
    private final String description;
}
