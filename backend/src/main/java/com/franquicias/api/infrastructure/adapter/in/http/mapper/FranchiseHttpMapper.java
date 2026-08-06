package com.franquicias.api.infrastructure.adapter.in.http.mapper;

import com.franquicias.api.domain.model.Franchise;
import com.franquicias.api.infrastructure.adapter.in.http.dto.franchise.CreateFranchiseRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.franchise.FranchiseResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.franchise.UpdateFranchiseRequest;

public class FranchiseHttpMapper {

    public Franchise toDomain(CreateFranchiseRequest request) {

        if (request == null) {
            return null;
        }

        return Franchise.builder()
                .name(request.getName())
                .build();
    }

    public Franchise toDomain(Long id, UpdateFranchiseRequest request) {

        if (request == null) {
            return null;
        }

        return Franchise.builder()
                .id(id)
                .name(request.getName())
                .build();
    }

    public FranchiseResponse toResponse(Franchise franchise) {

        if (franchise == null) {
            return null;
        }

        return FranchiseResponse.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .build();
    }

}