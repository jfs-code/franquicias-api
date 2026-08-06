package com.franquicias.api.domain.ports.in;

import com.franquicias.api.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductUseCase {

    Product create(Product product);

    Product updateStock(Long id, Integer stock);

    void delete(Long id);

    Optional<Product> findById(Long id);

    List<Product> findByBranch(Long branchId);

    List<Product> findTopStockProductsByFranchise(Long franchiseId);

}