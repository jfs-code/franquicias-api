package com.franquicias.api.infrastructure.adapter.in.http.dto.franchise;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FranchiseResponse {

    private Long id;

    private String name;

}