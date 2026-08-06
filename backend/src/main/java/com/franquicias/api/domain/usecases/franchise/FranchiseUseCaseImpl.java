package com.franquicias.api.domain.usecases.franchise;

import com.franquicias.api.domain.exception.DuplicateResourceException;
import com.franquicias.api.domain.exception.ResourceNotFoundException;
import com.franquicias.api.domain.model.Franchise;
import com.franquicias.api.domain.ports.in.FranchiseUseCase;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;

import java.util.List;

public class FranchiseUseCaseImpl implements FranchiseUseCase {

    private final FranchiseRepositoryPort franchiseRepository;

    public FranchiseUseCaseImpl(FranchiseRepositoryPort franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Franchise create(Franchise franchise) {

        if (franchiseRepository.existsByName(franchise.getName())) {
            throw new DuplicateResourceException(
                    "Franchise '" + franchise.getName() + "' already exists.");
        }

        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise updateName(Long id, String name) {

        Franchise franchise = franchiseRepository.findById(id)
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Franchise with id " + id + " not found."
                )
        );

        if (!franchise.getName().equalsIgnoreCase(name)
                && franchiseRepository.existsByName(name)) {

            throw new DuplicateResourceException(
                    "Franchise '" + name + "' already exists."
            );
        }

        franchise.setName(name);

        return franchiseRepository.save(franchise);
    }

    @Override
    public Franchise findById(Long id) {

        return franchiseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Franchise with id " + id + " not found."));
    }

    @Override
    public List<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

}