package com.franquicias.api.domain.usecases.franchise;

import com.franquicias.api.domain.exception.DuplicateResourceException;
import com.franquicias.api.domain.exception.ResourceNotFoundException;
import com.franquicias.api.domain.model.Franchise;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FranchiseUseCaseImplTest {

        @Mock
        private FranchiseRepositoryPort franchiseRepository;

        @InjectMocks
        private FranchiseUseCaseImpl franchiseUseCase;

        private Franchise franchise;

        @BeforeEach
        void setUp() {

                franchise = Franchise.builder()
                                .id(1L)
                                .name("Franquicia Bogotá")
                                .build();
        }

        @Test
        @DisplayName("Should create a franchise successfully")
        void shouldCreateFranchiseSuccessfully() {

                when(franchiseRepository.existsByName(franchise.getName()))
                                .thenReturn(false);

                when(franchiseRepository.save(franchise))
                                .thenReturn(franchise);

                Franchise result = franchiseUseCase.create(franchise);

                assertNotNull(result);
                assertEquals(franchise.getId(), result.getId());
                assertEquals(franchise.getName(), result.getName());

                verify(franchiseRepository).existsByName(franchise.getName());
                verify(franchiseRepository).save(franchise);
        }

        @Test
        @DisplayName("Should throw DuplicateResourceException when franchise already exists")
        void shouldThrowDuplicateResourceExceptionWhenCreatingDuplicatedFranchise() {

                when(franchiseRepository.existsByName(franchise.getName()))
                                .thenReturn(true);

                DuplicateResourceException exception = assertThrows(
                                DuplicateResourceException.class,
                                () -> franchiseUseCase.create(franchise));

                assertEquals(
                                "Franchise 'Franquicia Bogotá' already exists.",
                                exception.getMessage());

                verify(franchiseRepository).existsByName(franchise.getName());
                verify(franchiseRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should update franchise name successfully")
        void shouldUpdateFranchiseNameSuccessfully() {

                when(franchiseRepository.findById(1L))
                                .thenReturn(Optional.of(franchise));

                when(franchiseRepository.existsByName("Franquicia Medellín"))
                                .thenReturn(false);

                when(franchiseRepository.save(any(Franchise.class)))
                                .thenAnswer(invocation -> invocation.getArgument(0));

                Franchise result = franchiseUseCase.updateName(
                                1L,
                                "Franquicia Medellín");

                assertEquals("Franquicia Medellín", result.getName());

                verify(franchiseRepository).findById(1L);
                verify(franchiseRepository).existsByName("Franquicia Medellín");
                verify(franchiseRepository).save(franchise);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when updating non existing franchise")
        void shouldThrowResourceNotFoundExceptionWhenUpdatingNonExistingFranchise() {

                when(franchiseRepository.findById(1L))
                                .thenReturn(Optional.empty());

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> franchiseUseCase.updateName(1L, "Nueva"));

                assertEquals(
                                "Franchise with id 1 not found.",
                                exception.getMessage());

                verify(franchiseRepository).findById(1L);
                verify(franchiseRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw DuplicateResourceException when updating to an existing name")
        void shouldThrowDuplicateResourceExceptionWhenUpdatingDuplicatedName() {

                when(franchiseRepository.findById(1L))
                                .thenReturn(Optional.of(franchise));

                when(franchiseRepository.existsByName("Franquicia Medellín"))
                                .thenReturn(true);

                DuplicateResourceException exception = assertThrows(
                                DuplicateResourceException.class,
                                () -> franchiseUseCase.updateName(
                                                1L,
                                                "Franquicia Medellín"));

                assertEquals(
                                "Franchise 'Franquicia Medellín' already exists.",
                                exception.getMessage());

                verify(franchiseRepository).findById(1L);
                verify(franchiseRepository).existsByName("Franquicia Medellín");
                verify(franchiseRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should return franchise by id")
        void shouldReturnFranchiseById() {

                when(franchiseRepository.findById(1L))
                                .thenReturn(Optional.of(franchise));

                Franchise result = franchiseUseCase.findById(1L);

                assertNotNull(result);
                assertEquals(1L, result.getId());
                assertEquals("Franquicia Bogotá", result.getName());

                verify(franchiseRepository).findById(1L);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when franchise does not exist")
        void shouldThrowResourceNotFoundExceptionWhenFindingById() {

                when(franchiseRepository.findById(1L))
                                .thenReturn(Optional.empty());

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> franchiseUseCase.findById(1L));

                assertEquals(
                                "Franchise with id 1 not found.",
                                exception.getMessage());

                verify(franchiseRepository).findById(1L);
        }

        @Test
        @DisplayName("Should return all franchises")
        void shouldReturnAllFranchises() {

                Franchise second = Franchise.builder()
                                .id(2L)
                                .name("Franquicia Medellín")
                                .build();

                when(franchiseRepository.findAll())
                                .thenReturn(List.of(franchise, second));

                List<Franchise> result = franchiseUseCase.findAll();

                assertEquals(2, result.size());
                assertEquals("Franquicia Bogotá", result.get(0).getName());
                assertEquals("Franquicia Medellín", result.get(1).getName());

                verify(franchiseRepository).findAll();
        }
}
