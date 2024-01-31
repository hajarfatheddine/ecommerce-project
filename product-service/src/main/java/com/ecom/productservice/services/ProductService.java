package com.ecom.productservice.services;

import com.ecom.productservice.dto.product.ProductRequest;
import com.ecom.productservice.dto.product.ProductResponse;
import com.ecom.productservice.dao.Product;
import com.ecom.productservice.exceptions.BusinessException;
import com.ecom.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ValidationService validationService;
    public ProductResponse createProduct(ProductRequest productRequest) throws BusinessException {
        validationService.validate(productRequest);
        Product product = mapToProduct(productRequest);
        productRepository.save(product);
        return mapToProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
    private Product mapToProduct(ProductRequest productRequest){
        return   Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }
}
