package com.franquicias.api.infrastructure.adapter.in.http.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductStockRequest {

    @NotNull(message = "The stock is required")
    @Min(value = 0, message = "The stock cannot be negative")
    private Integer stock;

}