package com.ecom.inventoryservice.services;

import com.ecom.inventoryservice.dtos.inventory.InventoryResponse;
import com.ecom.inventoryservice.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Wait Started");
        Thread.sleep(20000);
        log.info("Wait Ended");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> {
                    return InventoryResponse.builder().skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity()>0)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
