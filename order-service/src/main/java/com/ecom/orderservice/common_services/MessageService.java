package com.ecom.orderservice.common_services;

import com.ecom.orderservice.enums.MessageCode;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageSource messageSource;
    public String get(MessageCode messageCode, String...arguments){
        try
        {
            return messageSource.getMessage(messageCode.name(), arguments, Locale.getDefault());
        }
        catch(Exception e){
            return "";
        }
    }
}
