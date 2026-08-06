package com.franquicias.api.infrastructure.adapter.in.http.dto.branch;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BranchResponse {

    private Long id;

    private String name;

    private Long franchiseId;

}