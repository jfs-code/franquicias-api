package com.franquicias.api.infrastructure.adapter.in.http.mapper;

import com.franquicias.api.domain.model.Product;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.CreateProductRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.ProductResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.TopStockProductResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.UpdateProductRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.UpdateProductStockRequest;

public class ProductHttpMapper {

    public Product toDomain(CreateProductRequest request) {

        if (request == null) {
            return null;
        }

        return Product.builder()
                .name(request.getName())
                .stock(request.getStock())
                .branchId(request.getBranchId())
                .build();
    }

    public Product toDomain(Long id, Long branchId, UpdateProductRequest request) {

        if (request == null) {
            return null;
        }

        return Product.builder()
                .id(id)
                .name(request.getName())
                .branchId(branchId)
                .build();
    }

    public Product toDomain(Long id, UpdateProductStockRequest request) {

        if (request == null) {
            return null;
        }

        return Product.builder()
                .id(id)
                .stock(request.getStock())
                .build();
    }

    public ProductResponse toResponse(Product product) {

        if (product == null) {
            return null;
        }

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .branchId(product.getBranchId())
                .build();
    }

    public TopStockProductResponse toTopStockResponse(Product product, String branchName) {

        if (product == null) {
            return null;
        }

        return TopStockProductResponse.builder()
                .branchId(product.getBranchId())
                .branchName(branchName)
                .productId(product.getId())
                .productName(product.getName())
                .stock(product.getStock())
                .build();
    }
}
