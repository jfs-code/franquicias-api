package com.franquicias.api.infrastructure.adapter.in.http.dto.branch;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBranchRequest {

    @NotBlank(message = "The branch name is required")
    private String name;

}