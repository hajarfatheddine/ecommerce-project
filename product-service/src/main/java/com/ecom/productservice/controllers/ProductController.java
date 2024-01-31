package com.ecom.productservice.controllers;

import com.ecom.productservice.dto.product.ProductRequest;
import com.ecom.productservice.dto.product.ProductResponse;
import com.ecom.productservice.dto.response.ResponseBodyFactory;
import com.ecom.productservice.dto.response.ResponseBody;
import com.ecom.productservice.exceptions.BusinessException;
import com.ecom.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ecom.productservice.enums.MessageCode.PRODUCT_CREATED;

@RestController
@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ResponseBodyFactory<ProductResponse> responseBodyFactory;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseBody<ProductResponse>> createProduct(@RequestBody ProductRequest productRequest)
    throws BusinessException {
        ProductResponse result = productService.createProduct(productRequest);
        return ResponseEntity.ok(responseBodyFactory.get(PRODUCT_CREATED,result));
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
