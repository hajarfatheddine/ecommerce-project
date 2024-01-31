package com.ecom.productservice.services;

import com.ecom.productservice.dto.product.ProductRequest;
import com.ecom.productservice.exceptions.BusinessException;
import com.ecom.productservice.exceptions.BusinessExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ecom.productservice.enums.MessageCode.*;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Component("ProductValidationService")
@RequiredArgsConstructor
public class ValidationService {

    private final BusinessExceptionFactory businessExceptionFactory;
    public void validate(ProductRequest productRequest) throws BusinessException {
        if (isEmpty(productRequest.getName())){
            throw businessExceptionFactory.get(PRODUCT_NAME_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
        if (isEmpty(productRequest.getDescription())){
            throw businessExceptionFactory.get(PRODUCT_DESCRIPTION_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
        if (productRequest.getPrice() == null){
            throw businessExceptionFactory.get(PRODUCT_PRICE_SHOULD_NOT_BE_NULL_OR_EMPTY);
        }
    }
}
