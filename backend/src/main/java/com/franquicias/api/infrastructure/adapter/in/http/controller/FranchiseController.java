package com.franquicias.api.infrastructure.adapter.in.http.controller;

import com.franquicias.api.domain.model.Franchise;
import com.franquicias.api.domain.ports.in.BranchUseCase;
import com.franquicias.api.domain.ports.in.FranchiseUseCase;
import com.franquicias.api.domain.ports.in.ProductUseCase;
import com.franquicias.api.infrastructure.adapter.in.http.dto.branch.BranchResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.franchise.CreateFranchiseRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.franchise.FranchiseResponse;
import com.franquicias.api.infrastructure.adapter.in.http.dto.franchise.UpdateFranchiseRequest;
import com.franquicias.api.infrastructure.adapter.in.http.dto.product.ProductResponse;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.BranchHttpMapper;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.FranchiseHttpMapper;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.ProductHttpMapper;
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
        private final BranchUseCase branchUseCase;
        private final ProductUseCase productUseCase;

        private final FranchiseHttpMapper franchiseMapper;
        private final BranchHttpMapper branchMapper;
        private final ProductHttpMapper productMapper;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public FranchiseResponse create(
                        @Valid @RequestBody CreateFranchiseRequest request) {

                Franchise franchise = franchiseMapper.toDomain(request);

                return franchiseMapper.toResponse(
                                franchiseUseCase.create(franchise));
        }

        @PutMapping("/{franchiseId}/name")
        public FranchiseResponse updateName(
                        @PathVariable Long franchiseId,
                        @Valid @RequestBody UpdateFranchiseRequest request) {

                return franchiseMapper.toResponse(
                                franchiseUseCase.updateName(franchiseId, request.getName()));
        }

        @GetMapping("/{franchiseId}")
        public FranchiseResponse findById(
                        @PathVariable Long franchiseId) {

                return franchiseMapper.toResponse(
                                franchiseUseCase.findById(franchiseId));
        }

        @GetMapping
        public List<FranchiseResponse> findAll() {

                return franchiseUseCase.findAll()
                                .stream()
                                .map(franchiseMapper::toResponse)
                                .toList();
        }

        @GetMapping("/{franchiseId}/branches")
        public List<BranchResponse> findBranches(
                        @PathVariable Long franchiseId) {

                return branchUseCase.findByFranchise(franchiseId)
                                .stream()
                                .map(branchMapper::toResponse)
                                .toList();
        }

        @GetMapping("/{franchiseId}/top-stock-products")
        public List<ProductResponse> findTopStockProducts(
                        @PathVariable Long franchiseId) {

                return productUseCase.findTopStockProductsByFranchise(franchiseId)
                                .stream()
                                .map(productMapper::toResponse)
                                .toList();
        }

}