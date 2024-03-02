package com.ecom.productservice.common_services;

import com.ecom.productservice.enums.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageSource messageSource;

    public String get(MessageCode messageCode, String... arguments){
        try{
            return messageSource.getMessage(messageCode.name(), arguments, Locale.getDefault());
        }catch (Exception e){
            return "";
        }
    }
}
