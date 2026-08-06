package com.franquicias.api.infrastructure.adapter.in.http.dto.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TopStockProductResponse {

    private Long branchId;

    private String branchName;

    private Long productId;

    private String productName;

    private Integer stock;

}