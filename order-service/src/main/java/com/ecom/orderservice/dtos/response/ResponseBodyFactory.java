package com.ecom.orderservice.dtos.response;

import com.ecom.orderservice.common_services.MessageService;
import com.ecom.orderservice.enums.MessageCode;
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
