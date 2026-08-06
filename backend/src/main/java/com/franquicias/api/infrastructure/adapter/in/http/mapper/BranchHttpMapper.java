package com.franquicias.api.infrastructure.adapter.in.http.mapper;

import com.franquicias.api.domain.model.Branch;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.BranchResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.CreateBranchRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.UpdateBranchRequest;

public class BranchHttpMapper {

    public Branch toDomain(CreateBranchRequest request) {

        if (request == null) {
            return null;
        }

        return Branch.builder()
                .name(request.getName())
                .franchiseId(request.getFranchiseId())
                .build();
    }

    public Branch toDomain(Long id, Long franchiseId, UpdateBranchRequest request) {

        if (request == null) {
            return null;
        }

        return Branch.builder()
                .id(id)
                .name(request.getName())
                .franchiseId(franchiseId)
                .build();
    }

    public BranchResponse toResponse(Branch branch) {

        if (branch == null) {
            return null;
        }

        return BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .franchiseId(branch.getFranchiseId())
                .build();
    }

}