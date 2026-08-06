package com.franquicias.api.infrastructure.adapter.out.database.mapper;

import com.franquicias.api.domain.model.Product;
import com.franquicias.api.infrastructure.adapter.out.database.entity.BranchEntity;
import com.franquicias.api.infrastructure.adapter.out.database.entity.ProductEntity;

public class ProductMapper {

    public Product toDomain(ProductEntity entity) {

        if (entity == null) {
            return null;
        }

        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .stock(entity.getStock())
                .branchId(entity.getBranch().getId())
                .build();
    }

    public ProductEntity toEntity(Product domain) {

        if (domain == null) {
            return null;
        }

        BranchEntity branch = BranchEntity.builder()
                .id(domain.getBranchId())
                .build();

        return ProductEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .stock(domain.getStock())
                .branch(branch)
                .build();
    }

}