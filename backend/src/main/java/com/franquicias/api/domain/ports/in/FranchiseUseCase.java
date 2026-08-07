package com.franquicias.api.domain.ports.in;

import com.franquicias.api.domain.model.Franchise;

import java.util.List;

public interface FranchiseUseCase {

    Franchise create(Franchise franchise);

    Franchise updateName(Long id, String name);

    Franchise findById(Long id);

    List<Franchise> findAll();

}
