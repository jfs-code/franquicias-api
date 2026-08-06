package com.franquicias.api.infrastructure.adapter.out.database.adapter;

import com.franquicias.api.domain.model.Product;
import com.franquicias.api.domain.ports.out.ProductRepositoryPort;
import com.franquicias.api.infrastructure.adapter.out.database.mapper.ProductMapper;
import com.franquicias.api.infrastructure.adapter.out.database.repository.JpaProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository repository;
    private final ProductMapper mapper;

    public ProductRepositoryAdapter(
            JpaProductRepository repository,
            ProductMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {

        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(product)));
    }

    @Override
    public Optional<Product> findById(Long id) {

        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findByBranchId(Long branchId) {

        return repository.findByBranchId(branchId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Product> findTopStockProductsByFranchise(Long franchiseId) {

        return repository.findTopStockProductsByFranchise(franchiseId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByNameAndBranchId(String name, Long branchId) {
        return repository.existsByNameAndBranchId(name, branchId);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
