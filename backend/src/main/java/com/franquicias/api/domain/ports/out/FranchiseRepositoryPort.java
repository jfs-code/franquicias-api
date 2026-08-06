package com.franquicias.api.domain.ports.out;

import com.franquicias.api.domain.model.Franchise;

import java.util.List;
import java.util.Optional;

public interface FranchiseRepositoryPort {

    Franchise save(Franchise franchise);

    Optional<Franchise> findById(Long id);

    Optional<Franchise> findByName(String name);

    List<Franchise> findAll();

    boolean existsById(Long id);

    boolean existsByName(String name);

    void deleteById(Long id);

}