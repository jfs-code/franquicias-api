package com.franquicias.api.infrastructure.config;

import com.franquicias.api.infrastructure.adapter.in.http.mapper.BranchHttpMapper;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.FranchiseHttpMapper;
import com.franquicias.api.infrastructure.adapter.in.http.mapper.ProductHttpMapper;
import com.franquicias.api.infrastructure.adapter.out.database.mapper.BranchMapper;
import com.franquicias.api.infrastructure.adapter.out.database.mapper.FranchiseMapper;
import com.franquicias.api.infrastructure.adapter.out.database.mapper.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureBeanConfig {

    @Bean
    public FranchiseMapper franchiseMapper() {
        return new FranchiseMapper();
    }

    @Bean
    public BranchMapper branchMapper() {
        return new BranchMapper();
    }

    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }

    @Bean
    public FranchiseHttpMapper franchiseHttpMapper() {
        return new FranchiseHttpMapper();
    }

    @Bean
    public BranchHttpMapper branchHttpMapper() {
        return new BranchHttpMapper();
    }

    @Bean
    public ProductHttpMapper productHttpMapper() {
        return new ProductHttpMapper();
    }

}