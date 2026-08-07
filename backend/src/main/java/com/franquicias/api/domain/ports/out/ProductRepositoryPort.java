package com.franquicias.api.domain.ports.out;

import com.franquicias.api.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findByBranchId(Long branchId);

    List<Product> findTopStockProductsByFranchise(Long franchiseId);

    boolean existsById(Long id);

    boolean existsByNameAndBranchId(String name, Long branchId);

    void deleteById(Long id);

}
