package com.franquicias.api.infrastructure.adapter.out.database.mapper;

import com.franquicias.api.domain.model.Franchise;
import com.franquicias.api.infrastructure.adapter.out.database.entity.FranchiseEntity;

public class FranchiseMapper {

    public Franchise toDomain(FranchiseEntity entity) {

        if (entity == null) {
            return null;
        }

        return Franchise.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public FranchiseEntity toEntity(Franchise domain) {

        if (domain == null) {
            return null;
        }

        return FranchiseEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }

}