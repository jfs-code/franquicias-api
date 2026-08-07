package com.franquicias.api.domain.ports.in;

import com.franquicias.api.domain.model.Branch;

import java.util.List;

public interface BranchUseCase {

    Branch create(Branch branch);

    Branch updateName(Long id, String name);

    Branch findById(Long id);

    List<Branch> findByFranchise(Long franchiseId);

}
