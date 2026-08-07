package com.franquicias.api.infrastructure.config;

import com.franquicias.api.domain.ports.in.BranchUseCase;
import com.franquicias.api.domain.ports.in.FranchiseUseCase;
import com.franquicias.api.domain.ports.in.ProductUseCase;
import com.franquicias.api.domain.ports.out.BranchRepositoryPort;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;
import com.franquicias.api.domain.ports.out.ProductRepositoryPort;
import com.franquicias.api.domain.usecases.branch.BranchUseCaseImpl;
import com.franquicias.api.domain.usecases.franchise.FranchiseUseCaseImpl;
import com.franquicias.api.domain.usecases.product.ProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public FranchiseUseCase franchiseUseCase(
            FranchiseRepositoryPort franchiseRepositoryPort) {

        return new FranchiseUseCaseImpl(franchiseRepositoryPort);
    }

    @Bean
    public BranchUseCase branchUseCase(
            BranchRepositoryPort branchRepositoryPort,
            FranchiseRepositoryPort franchiseRepositoryPort) {

        return new BranchUseCaseImpl(
                branchRepositoryPort,
                franchiseRepositoryPort);
    }

    @Bean
    public ProductUseCase productUseCase(
            ProductRepositoryPort productRepositoryPort,
            BranchRepositoryPort branchRepositoryPort,
            FranchiseRepositoryPort franchiseRepositoryPort) {

        return new ProductUseCaseImpl(
                productRepositoryPort,
                branchRepositoryPort,
                franchiseRepositoryPort);
    }
}
