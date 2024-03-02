package com.ecom.orderservice.exceptions;

import com.ecom.orderservice.common_services.MessageService;
import com.ecom.orderservice.enums.MessageCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BusinessExceptionFactory {
    private final MessageService messageService;
    public BusinessException get(MessageCode messageCode, String... arguments){
        return new BusinessException(messageCode,messageService.get(messageCode,arguments));
    }
}
