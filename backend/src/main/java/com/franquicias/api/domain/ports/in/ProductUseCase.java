package com.franquicias.api.domain.ports.in;

import com.franquicias.api.domain.model.Product;

import java.util.List;

public interface ProductUseCase {

    Product create(Product product);

    Product updateStock(Long id, Integer stock);

    void delete(Long id);

    Product findById(Long id);

    Product updateName(Long id, String name);

    List<Product> findByBranch(Long branchId);

    List<Product> findTopStockProductsByFranchise(Long franchiseId);

}
