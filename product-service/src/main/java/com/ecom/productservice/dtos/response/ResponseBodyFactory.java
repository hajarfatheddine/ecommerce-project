package com.ecom.productservice.dtos.response;

import com.ecom.productservice.common_services.MessageService;
import com.ecom.productservice.enums.MessageCode;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Generated
public class ResponseBodyFactory<T> {

    private final MessageService messageService;
    public ResponseBody<T> get(MessageCode code, T result){
        return ResponseBody.<T>builder()
                .code(code.name())
                .description(messageService.get(code))
                .result(result)
                .build();
    }
}
