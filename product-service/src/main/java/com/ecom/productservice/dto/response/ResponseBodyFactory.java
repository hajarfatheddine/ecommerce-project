package com.ecom.productservice.dto.response;

import com.ecom.productservice.common.MessageService;
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
                .message(messageService.get(code))
                .result(result)
                .build();
    }
}
