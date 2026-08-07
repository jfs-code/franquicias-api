package com.franquicias.api.infrastructure.adapter.out.database.mapper;

import com.franquicias.api.domain.model.Branch;
import com.franquicias.api.infrastructure.adapter.out.database.entity.BranchEntity;
import com.franquicias.api.infrastructure.adapter.out.database.entity.FranchiseEntity;

public class BranchMapper {

    public Branch toDomain(BranchEntity entity) {

        if (entity == null) {
            return null;
        }

        return Branch.builder()
                .id(entity.getId())
                .name(entity.getName())
                .franchiseId(entity.getFranchise().getId())
                .build();
    }

    public BranchEntity toEntity(Branch domain) {

        if (domain == null) {
            return null;
        }

        FranchiseEntity franchise = FranchiseEntity.builder()
                .id(domain.getFranchiseId())
                .build();

        return BranchEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .franchise(franchise)
                .build();
    }
}
