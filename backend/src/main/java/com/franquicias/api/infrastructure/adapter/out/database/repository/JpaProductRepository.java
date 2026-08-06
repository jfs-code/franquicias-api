package com.franquicias.api.infrastructure.adapter.out.database.repository;

import com.franquicias.api.infrastructure.adapter.out.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByBranchId(Long branchId);

    boolean existsByNameAndBranchId(String name, Long branchId);

    @Query("""
        SELECT p
        FROM ProductEntity p
        WHERE p.branch.franchise.id = :franchiseId
          AND p.stock = (
                SELECT MAX(p2.stock)
                FROM ProductEntity p2
                WHERE p2.branch.id = p.branch.id
          )
    """)
    List<ProductEntity> findTopStockProductsByFranchise(
            @Param("franchiseId") Long franchiseId);
}