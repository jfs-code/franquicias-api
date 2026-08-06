package com.franquicias.api.infrastructure.adapter.out.database.repository;

import com.franquicias.api.infrastructure.adapter.out.database.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBranchRepository extends JpaRepository<BranchEntity, Long> {

    List<BranchEntity> findByFranchiseId(Long franchiseId);

    boolean existsByNameAndFranchiseId(String name, Long franchiseId);

}