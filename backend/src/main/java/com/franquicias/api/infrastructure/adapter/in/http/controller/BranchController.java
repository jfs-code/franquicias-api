package com.franquicias.api.infrastructure.adapter.in.http.controller;

import com.franquicias.api.domain.ports.in.BranchUseCase;
import com.franquicias.api.domain.ports.in.ProductUseCase;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.BranchResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.CreateBranchRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.UpdateBranchRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.ProductResponse;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.BranchHttpMapper;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.ProductHttpMapper;
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
        private final ProductUseCase productUseCase;

        private final BranchHttpMapper branchMapper;
        private final ProductHttpMapper productMapper;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public BranchResponse create(
                        @Valid @RequestBody CreateBranchRequest request) {

                return branchMapper.toResponse(
                                branchUseCase.create(
                                                branchMapper.toDomain(request)));
        }

        @PutMapping("/{branchId}/name")
        public BranchResponse updateName(
                        @PathVariable Long branchId,
                        @Valid @RequestBody UpdateBranchRequest request) {

                return branchMapper.toResponse(
                                branchUseCase.updateName(branchId, request.getName()));
        }

        @GetMapping("/{branchId}")
        public BranchResponse findById(
                        @PathVariable Long branchId) {

                return branchMapper.toResponse(
                                branchUseCase.findById(branchId));
        }

        @GetMapping("/{branchId}/products")
        public List<ProductResponse> findProducts(
                        @PathVariable Long branchId) {

                return productUseCase.findByBranch(branchId)
                                .stream()
                                .map(productMapper::toResponse)
                                .toList();
        }

}