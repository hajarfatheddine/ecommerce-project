package com.ecom.productservice.common;

import com.ecom.productservice.enums.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageSource messageSource;

    public String get(MessageCode code, String... args){
        try{
                return messageSource.getMessage(code.name(), args, Locale.getDefault());
        }catch (Exception e){
            return "";
        }
    }
}
