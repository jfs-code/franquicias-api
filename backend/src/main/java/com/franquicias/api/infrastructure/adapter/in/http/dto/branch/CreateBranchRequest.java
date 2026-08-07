package com.franquicias.api.infrastructure.adapter.in.http.dto.branch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBranchRequest {

    @NotBlank(message = "The branch name is required")
    private String name;

    @NotNull(message = "The franchise id is required")
    private Long franchiseId;

}
