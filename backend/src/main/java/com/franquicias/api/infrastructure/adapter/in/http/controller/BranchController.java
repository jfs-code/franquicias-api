package com.franquicias.api.infrastructure.adapter.in.http.controller;

import com.franquicias.api.domain.ports.in.BranchUseCase;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.BranchResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.CreateBranchRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.UpdateBranchRequest;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.BranchHttpMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchUseCase branchUseCase;
    private final BranchHttpMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BranchResponse create(
            @Valid @RequestBody CreateBranchRequest request) {

        return mapper.toResponse(
                branchUseCase.create(
                        mapper.toDomain(request)
                )
        );
    }

    @PutMapping("/{id}/name")
    public BranchResponse updateName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBranchRequest request) {

        return mapper.toResponse(
                branchUseCase.updateName(id, request.getName())
        );
    }

    @GetMapping("/{id}")
    public BranchResponse findById(@PathVariable Long id) {

        return branchUseCase.findById(id)
                .map(mapper::toResponse)
                .orElseThrow();
    }

    @GetMapping("/franchise/{franchiseId}")
    public List<BranchResponse> findByFranchise(
            @PathVariable Long franchiseId) {

        return branchUseCase.findByFranchise(franchiseId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

}