package com.ecom.productservice.exceptions;

import com.ecom.productservice.common.MessageService;
import com.ecom.productservice.enums.MessageCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BusinessExceptionFactory {
    private final MessageService messageService;
    public BusinessException get(MessageCode code, String... args){
        return new BusinessException(code,messageService.get(code,args));
    }
}
