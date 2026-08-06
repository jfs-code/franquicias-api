package com.franquicias.api.infrastructure.adapter.in.http.controller;

import com.franquicias.api.domain.model.Product;
import com.franquicias.api.domain.ports.in.ProductUseCase;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.CreateProductRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.ProductResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.UpdateProductStockRequest;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.ProductHttpMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductHttpMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(
            @Valid @RequestBody CreateProductRequest request) {

        Product product = mapper.toDomain(request);

        return mapper.toResponse(
                productUseCase.create(product)
        );
    }

    @PatchMapping("/{id}/stock")
    public ProductResponse updateStock(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductStockRequest request) {

        return mapper.toResponse(
                productUseCase.updateStock(id, request.getStock())
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productUseCase.delete(id);
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) {

        return productUseCase.findById(id)
                .map(mapper::toResponse)
                .orElseThrow();
    }

}