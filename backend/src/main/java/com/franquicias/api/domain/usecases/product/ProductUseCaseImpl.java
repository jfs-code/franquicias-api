package com.franquicias.api.domain.usecases.product;

import com.franquicias.api.domain.exception.DuplicateResourceException;
import com.franquicias.api.domain.exception.ResourceNotFoundException;
import com.franquicias.api.domain.model.Product;
import com.franquicias.api.domain.ports.in.ProductUseCase;
import com.franquicias.api.domain.ports.out.BranchRepositoryPort;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;
import com.franquicias.api.domain.ports.out.ProductRepositoryPort;

import java.util.List;

public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductRepositoryPort productRepository;
    private final BranchRepositoryPort branchRepository;
    private final FranchiseRepositoryPort franchiseRepository;

    public ProductUseCaseImpl(
            ProductRepositoryPort productRepository,
            BranchRepositoryPort branchRepository,
            FranchiseRepositoryPort franchiseRepository) {

        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Product create(Product product) {

        if (!branchRepository.existsById(product.getBranchId())) {
            throw new ResourceNotFoundException(
                    "Branch with id " + product.getBranchId() + " not found.");
        }

        if (productRepository.existsByNameAndBranchId(
                product.getName(),
                product.getBranchId())) {

            throw new DuplicateResourceException(
                    "Product '" + product.getName() + "' already exists in the branch.");
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateStock(Long id, Integer stock) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + id + " not found."));

        product.setStock(stock);

        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Product with id " + id + " not found.");
        }

        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + id + " not found."));
    }

    @Override
    public List<Product> findByBranch(Long branchId) {

        if (!branchRepository.existsById(branchId)) {
            throw new ResourceNotFoundException(
                    "Branch with id " + branchId + " not found.");
        }

        return productRepository.findByBranchId(branchId);
    }

    @Override
    public List<Product> findTopStockProductsByFranchise(Long franchiseId) {

        if (!franchiseRepository.existsById(franchiseId)) {
            throw new ResourceNotFoundException(
                    "Franchise with id " + franchiseId + " not found.");
        }

        return productRepository.findTopStockProductsByFranchise(franchiseId);
    }

    @Override
    public Product updateName(Long id, String name) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id " + id + " not found."));

        if (productRepository.existsByNameAndBranchId(
                name,
                product.getBranchId())) {

            throw new DuplicateResourceException(
                    "Product '" + name + "' already exists in the branch.");
        }

        product.setName(name);

        return productRepository.save(product);
    }
}
