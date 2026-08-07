package com.franquicias.api.infrastructure.adapter.in.http.controller;

import com.franquicias.api.domain.model.Product;
import com.franquicias.api.domain.ports.in.ProductUseCase;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.CreateProductRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.ProductResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.UpdateProductNameRequest;
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
                                productUseCase.create(product));
        }

        @PutMapping("/{productId}/name")
        public ProductResponse updateName(
                        @PathVariable Long productId,
                        @Valid @RequestBody UpdateProductNameRequest request) {

                return mapper.toResponse(
                                productUseCase.updateName(productId, request.getName()));
        }

        @PatchMapping("/{productId}/stock")
        public ProductResponse updateStock(
                        @PathVariable Long productId,
                        @Valid @RequestBody UpdateProductStockRequest request) {

                return mapper.toResponse(
                                productUseCase.updateStock(productId, request.getStock()));
        }

        @DeleteMapping("/{productId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void delete(
                        @PathVariable Long productId) {

                productUseCase.delete(productId);
        }

        @GetMapping("/{productId}")
        public ProductResponse findById(
                        @PathVariable Long productId) {

                return mapper.toResponse(
                                productUseCase.findById(productId));
        }
}
