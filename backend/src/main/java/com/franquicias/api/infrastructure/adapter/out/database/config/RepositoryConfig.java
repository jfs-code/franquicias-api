package com.franquicias.api.infrastructure.adapter.out.database.config;

import com.franquicias.api.domain.ports.out.BranchRepositoryPort;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;
import com.franquicias.api.domain.ports.out.ProductRepositoryPort;
import com.franquicias.api.infrastructure.adapter.out.database.adapter.BranchRepositoryAdapter;
import com.franquicias.api.infrastructure.adapter.out.database.adapter.FranchiseRepositoryAdapter;
import com.franquicias.api.infrastructure.adapter.out.database.adapter.ProductRepositoryAdapter;
import com.franquicias.api.infrastructure.adapter.out.database.mapper.BranchMapper;
import com.franquicias.api.infrastructure.adapter.out.database.mapper.FranchiseMapper;
import com.franquicias.api.infrastructure.adapter.out.database.mapper.ProductMapper;
import com.franquicias.api.infrastructure.adapter.out.database.repository.JpaBranchRepository;
import com.franquicias.api.infrastructure.adapter.out.database.repository.JpaFranchiseRepository;
import com.franquicias.api.infrastructure.adapter.out.database.repository.JpaProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public FranchiseRepositoryPort franchiseRepositoryPort(
            JpaFranchiseRepository repository,
            FranchiseMapper mapper) {

        return new FranchiseRepositoryAdapter(
                repository,
                mapper
        );
    }

    @Bean
    public BranchRepositoryPort branchRepositoryPort(
            JpaBranchRepository repository,
            BranchMapper mapper) {

        return new BranchRepositoryAdapter(
                repository,
                mapper
        );
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(
            JpaProductRepository repository,
            ProductMapper mapper) {

        return new ProductRepositoryAdapter(
                repository,
                mapper
        );
    }

}