package com.franquicias.api.infrastructure.adapter.in.http.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {

    @NotBlank(message = "The product name is required")
    private String name;

}