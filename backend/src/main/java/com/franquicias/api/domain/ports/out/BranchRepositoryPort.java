package com.franquicias.api.domain.ports.out;

import com.franquicias.api.domain.model.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepositoryPort {

    Branch save(Branch branch);

    Optional<Branch> findById(Long id);

    List<Branch> findByFranchiseId(Long franchiseId);

    boolean existsById(Long id);

    boolean existsByNameAndFranchiseId(String name, Long franchiseId);

    void deleteById(Long id);

}
