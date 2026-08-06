package com.franquicias.api.infrastructure.adapter.in.http.dto.franchise;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFranchiseRequest {

    @NotBlank(message = "The franchise name is required")
    private String name;

}