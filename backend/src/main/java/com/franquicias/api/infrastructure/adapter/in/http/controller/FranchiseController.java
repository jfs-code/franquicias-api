package com.franquicias.api.infrastructure.adapter.in.http.controller;

import com.franquicias.api.domain.model.Franchise;
import com.franquicias.api.domain.ports.in.FranchiseUseCase;
import com.franquicias.api.infrastructure.adapter.in.http.dto.franchise.CreateFranchiseRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.franchise.FranchiseResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.franchise.UpdateFranchiseRequest;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.FranchiseHttpMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseUseCase franchiseUseCase;
    private final FranchiseHttpMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FranchiseResponse create(
            @Valid @RequestBody CreateFranchiseRequest request) {

        Franchise franchise = mapper.toDomain(request);

        return mapper.toResponse(
                franchiseUseCase.create(franchise)
        );
    }

    @PutMapping("/{id}/name")
    public FranchiseResponse updateName(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFranchiseRequest request) {

        return mapper.toResponse(
                franchiseUseCase.updateName(id, request.getName())
        );
    }

    @GetMapping("/{id}")
    public FranchiseResponse findById(@PathVariable Long id) {

        return franchiseUseCase.findById(id)
                .map(mapper::toResponse)
                .orElseThrow();
    }

    @GetMapping
    public List<FranchiseResponse> findAll() {

        return franchiseUseCase.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

}