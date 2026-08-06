package com.franquicias.api.infrastructure.adapter.out.database.adapter;

import com.franquicias.api.domain.model.Franchise;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;
import com.franquicias.api.infrastructure.adapter.out.database.mapper.FranchiseMapper;
import com.franquicias.api.infrastructure.adapter.out.database.repository.JpaFranchiseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FranchiseRepositoryAdapter implements FranchiseRepositoryPort {

    private final JpaFranchiseRepository repository;
    private final FranchiseMapper mapper;

    public FranchiseRepositoryAdapter(
            JpaFranchiseRepository repository,
            FranchiseMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Franchise save(Franchise franchise) {

        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(franchise)));
    }

    @Override
    public Optional<Franchise> findById(Long id) {

        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Franchise> findByName(String name) {

        return repository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public List<Franchise> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
