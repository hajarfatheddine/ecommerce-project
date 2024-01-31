package com.ecom.productservice.config;

import com.ecom.productservice.common.MessageService;
import com.ecom.productservice.dto.response.ResponseBody;
import com.ecom.productservice.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import static com.ecom.productservice.enums.MessageCode.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class BusinessExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseBody<Object>> handle(BusinessException e){
        ResponseBody<Object> responseBody = ResponseBody.builder()
                .code(e.getCode().name())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseBody<Object>> handle(Throwable throwable){
        String code = "";
        String message = messageService.get(INTERNAL_SERVER_ERROR);
        log.error("Exception code: " + code);
        log.error("Exception stack trace: " + throwable);
        ResponseBody<Object> responseBody = ResponseBody.builder()
                .code(code)
                .message(message)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
