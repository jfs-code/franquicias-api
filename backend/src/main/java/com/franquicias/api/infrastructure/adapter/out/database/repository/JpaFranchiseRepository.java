package com.franquicias.api.infrastructure.adapter.out.database.repository;

import com.franquicias.api.infrastructure.adapter.out.database.entity.FranchiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaFranchiseRepository extends JpaRepository<FranchiseEntity, Long> {

    Optional<FranchiseEntity> findByName(String name);

    boolean existsByName(String name);

}
