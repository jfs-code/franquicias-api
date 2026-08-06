package com.franquicias.api.domain.usecases.franchise;

import com.franquicias.api.domain.model.Franchise;
import com.franquicias.api.domain.ports.in.FranchiseUseCase;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;

import java.util.List;
import java.util.Optional;

public class FranchiseUseCaseImpl implements FranchiseUseCase {

    private final FranchiseRepositoryPort franchiseRepository;

    public FranchiseUseCaseImpl(FranchiseRepositoryPort franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Franchise create(Franchise franchise) {

        if (franchiseRepository.existsByName(franchise.getName())) {
            throw new IllegalArgumentException("The franchise already exists.");
        }

        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise updateName(Long id, String name) {

        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Franchise not found."));

        franchise.setName(name);

        return franchiseRepository.save(franchise);
    }

    @Override
    public Optional<Franchise> findById(Long id) {
        return franchiseRepository.findById(id);
    }

    @Override
    public List<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

}