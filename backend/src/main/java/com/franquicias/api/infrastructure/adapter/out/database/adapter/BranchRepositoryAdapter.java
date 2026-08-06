package com.franquicias.api.infrastructure.adapter.out.database.adapter;

import com.franquicias.api.domain.model.Branch;
import com.franquicias.api.domain.ports.out.BranchRepositoryPort;
import com.franquicias.api.infrastructure.adapter.out.database.mapper.BranchMapper;
import com.franquicias.api.infrastructure.adapter.out.database.repository.JpaBranchRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BranchRepositoryAdapter implements BranchRepositoryPort {

    private final JpaBranchRepository repository;
    private final BranchMapper mapper;

    public BranchRepositoryAdapter(
            JpaBranchRepository repository,
            BranchMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Branch save(Branch branch) {

        return mapper.toDomain(
                repository.save(
                        mapper.toEntity(branch)
                )
        );
    }

    @Override
    public Optional<Branch> findById(Long id) {

        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Branch> findByFranchiseId(Long franchiseId) {

        return repository.findByFranchiseId(franchiseId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByNameAndFranchiseId(String name, Long franchiseId) {
        return repository.existsByNameAndFranchiseId(name, franchiseId);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
