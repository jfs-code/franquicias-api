package com.franquicias.api.domain.usecases.product;

import com.franquicias.api.domain.model.Product;
import com.franquicias.api.domain.ports.in.ProductUseCase;
import com.franquicias.api.domain.ports.out.BranchRepositoryPort;
import com.franquicias.api.domain.ports.out.ProductRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductRepositoryPort productRepository;
    private final BranchRepositoryPort branchRepository;

    public ProductUseCaseImpl(
            ProductRepositoryPort productRepository,
            BranchRepositoryPort branchRepository) {

        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Product create(Product product) {

        if (!branchRepository.existsById(product.getBranchId())) {
            throw new IllegalArgumentException("Branch not found.");
        }

        if (productRepository.existsByNameAndBranchId(
                product.getName(),
                product.getBranchId())) {

            throw new IllegalArgumentException("The product already exists.");
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateStock(Long id, Integer stock) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found."));

        product.setStock(stock);

        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {

        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found.");
        }

        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByBranch(Long branchId) {
        return productRepository.findByBranchId(branchId);
    }

    @Override
    public List<Product> findTopStockProductsByFranchise(Long franchiseId) {
        return productRepository.findTopStockProductsByFranchise(franchiseId);
    }
}