package com.franquicias.api.domain.usecases.branch;

import com.franquicias.api.domain.model.Branch;
import com.franquicias.api.domain.ports.in.BranchUseCase;
import com.franquicias.api.domain.ports.out.BranchRepositoryPort;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;

import java.util.List;
import java.util.Optional;

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
            throw new IllegalArgumentException("Franchise not found.");
        }

        if (branchRepository.existsByNameAndFranchiseId(
                branch.getName(),
                branch.getFranchiseId())) {

            throw new IllegalArgumentException("The branch already exists.");
        }

        return branchRepository.save(branch);
    }

    @Override
    public Branch updateName(Long id, String name) {

        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Branch not found."));

        branch.setName(name);

        return branchRepository.save(branch);
    }

    @Override
    public Optional<Branch> findById(Long id) {
        return branchRepository.findById(id);
    }

    @Override
    public List<Branch> findByFranchise(Long franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId);
    }
}