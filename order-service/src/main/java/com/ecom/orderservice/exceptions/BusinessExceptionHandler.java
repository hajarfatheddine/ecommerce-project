package com.ecom.orderservice.exceptions;

import com.ecom.orderservice.common_services.MessageService;
import com.ecom.orderservice.dtos.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import static com.ecom.orderservice.enums.MessageCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class BusinessExceptionHandler {
    private final MessageService messageService;
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
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
