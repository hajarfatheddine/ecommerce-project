package com.ecom.inventoryservice.exceptions;

import com.ecom.inventoryservice.common_services.MessageService;
import com.ecom.inventoryservice.dtos.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ecom.inventoryservice.enums.MessageCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class BusinessExceptionHandler {
    private final MessageService messageService;
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseBody<Object>> handle(BusinessException e){
        ResponseBody<Object> result = ResponseBody.builder()
                .code(e.getCode().name())
                .description(e.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler(Throwable.class)
    private ResponseEntity<ResponseBody<Object>> handle(Throwable throwable){
        String message = messageService.get(INTERNAL_SERVER_ERROR);
        log.error("Exception status:"+ INTERNAL_SERVER_ERROR.name());
        log.error("Exception stack trace: " + throwable);
        ResponseBody<Object> responseBody = ResponseBody.builder()
                .code(INTERNAL_SERVER_ERROR.name())
                .description(message)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

}
