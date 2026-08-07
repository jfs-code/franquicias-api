package com.franquicias.api.domain.usecases.branch;

import com.franquicias.api.domain.exception.DuplicateResourceException;
import com.franquicias.api.domain.exception.ResourceNotFoundException;
import com.franquicias.api.domain.model.Branch;
import com.franquicias.api.domain.ports.in.BranchUseCase;
import com.franquicias.api.domain.ports.out.BranchRepositoryPort;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;

import java.util.List;

public class BranchUseCaseImpl implements BranchUseCase {

    private final BranchRepositoryPort branchRepository;
    private final FranchiseRepositoryPort franchiseRepository;

    public BranchUseCaseImpl(
            BranchRepositoryPort branchRepository,
            FranchiseRepositoryPort franchiseRepository) {

        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Branch create(Branch branch) {

        if (!franchiseRepository.existsById(branch.getFranchiseId())) {
            throw new ResourceNotFoundException(
                    "Franchise with id " + branch.getFranchiseId() + " not found.");
        }

        if (branchRepository.existsByNameAndFranchiseId(
                branch.getName(),
                branch.getFranchiseId())) {

            throw new DuplicateResourceException(
                    "Branch '" + branch.getName() + "' already exists in the franchise.");
        }

        return branchRepository.save(branch);
    }

    @Override
    public Branch updateName(Long id, String name) {

        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Branch with id " + id + " not found."));

        branch.setName(name);

        return branchRepository.save(branch);
    }

    @Override
    public Branch findById(Long id) {

        return branchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Branch with id " + id + " not found."));
    }

    @Override
    public List<Branch> findByFranchise(Long franchiseId) {

        if (!franchiseRepository.existsById(franchiseId)) {
            throw new ResourceNotFoundException(
                    "Franchise with id " + franchiseId + " not found.");
        }

        return branchRepository.findByFranchiseId(franchiseId);
    }
}
