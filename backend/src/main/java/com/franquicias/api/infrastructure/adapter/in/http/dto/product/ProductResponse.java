package com.franquicias.api.infrastructure.adapter.in.http.dto.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private Integer stock;

    private Long branchId;

}