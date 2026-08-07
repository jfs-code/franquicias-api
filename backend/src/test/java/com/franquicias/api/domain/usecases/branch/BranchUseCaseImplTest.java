package com.franquicias.api.domain.usecases.branch;

import com.franquicias.api.domain.exception.DuplicateResourceException;
import com.franquicias.api.domain.exception.ResourceNotFoundException;
import com.franquicias.api.domain.model.Branch;
import com.franquicias.api.domain.ports.out.BranchRepositoryPort;
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
class BranchUseCaseImplTest {

        @Mock
        private BranchRepositoryPort branchRepository;

        @Mock
        private FranchiseRepositoryPort franchiseRepository;

        @InjectMocks
        private BranchUseCaseImpl branchUseCase;

        private Branch branch;

        @BeforeEach
        void setUp() {

                branch = Branch.builder()
                                .id(1L)
                                .name("Sucursal Centro")
                                .franchiseId(1L)
                                .build();
        }

        @Test
        @DisplayName("Should create a branch successfully")
        void shouldCreateBranchSuccessfully() {

                when(franchiseRepository.existsById(1L))
                                .thenReturn(true);

                when(branchRepository.existsByNameAndFranchiseId(
                                "Sucursal Centro",
                                1L))
                                .thenReturn(false);

                when(branchRepository.save(branch))
                                .thenReturn(branch);

                Branch result = branchUseCase.create(branch);

                assertNotNull(result);
                assertEquals(branch.getId(), result.getId());
                assertEquals(branch.getName(), result.getName());

                verify(franchiseRepository).existsById(1L);
                verify(branchRepository)
                                .existsByNameAndFranchiseId("Sucursal Centro", 1L);
                verify(branchRepository).save(branch);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when franchise does not exist")
        void shouldThrowResourceNotFoundExceptionWhenCreatingBranch() {

                when(franchiseRepository.existsById(1L))
                                .thenReturn(false);

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> branchUseCase.create(branch));

                assertEquals(
                                "Franchise with id 1 not found.",
                                exception.getMessage());

                verify(franchiseRepository).existsById(1L);
                verify(branchRepository, never())
                                .existsByNameAndFranchiseId(any(), any());
                verify(branchRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw DuplicateResourceException when branch already exists")
        void shouldThrowDuplicateResourceExceptionWhenCreatingDuplicatedBranch() {

                when(franchiseRepository.existsById(1L))
                                .thenReturn(true);

                when(branchRepository.existsByNameAndFranchiseId(
                                "Sucursal Centro",
                                1L))
                                .thenReturn(true);

                DuplicateResourceException exception = assertThrows(
                                DuplicateResourceException.class,
                                () -> branchUseCase.create(branch));

                assertEquals(
                                "Branch 'Sucursal Centro' already exists in the franchise.",
                                exception.getMessage());

                verify(franchiseRepository).existsById(1L);
                verify(branchRepository)
                                .existsByNameAndFranchiseId("Sucursal Centro", 1L);
                verify(branchRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should update branch name successfully")
        void shouldUpdateBranchNameSuccessfully() {

                when(branchRepository.findById(1L))
                                .thenReturn(Optional.of(branch));

                when(branchRepository.save(any(Branch.class)))
                                .thenAnswer(invocation -> invocation.getArgument(0));

                Branch result = branchUseCase.updateName(
                                1L,
                                "Sucursal Norte");

                assertEquals("Sucursal Norte", result.getName());

                verify(branchRepository).findById(1L);
                verify(branchRepository).save(branch);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when updating non existing branch")
        void shouldThrowResourceNotFoundExceptionWhenUpdatingBranch() {

                when(branchRepository.findById(1L))
                                .thenReturn(Optional.empty());

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> branchUseCase.updateName(1L, "Sucursal Norte"));

                assertEquals(
                                "Branch with id 1 not found.",
                                exception.getMessage());

                verify(branchRepository).findById(1L);
                verify(branchRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should return branch by id")
        void shouldReturnBranchById() {

                when(branchRepository.findById(1L))
                                .thenReturn(Optional.of(branch));

                Branch result = branchUseCase.findById(1L);

                assertNotNull(result);
                assertEquals(1L, result.getId());
                assertEquals("Sucursal Centro", result.getName());

                verify(branchRepository).findById(1L);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when branch does not exist")
        void shouldThrowResourceNotFoundExceptionWhenFindingBranch() {

                when(branchRepository.findById(1L))
                                .thenReturn(Optional.empty());

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> branchUseCase.findById(1L));

                assertEquals(
                                "Branch with id 1 not found.",
                                exception.getMessage());

                verify(branchRepository).findById(1L);
        }

        @Test
        @DisplayName("Should return branches by franchise")
        void shouldReturnBranchesByFranchise() {

                Branch second = Branch.builder()
                                .id(2L)
                                .name("Sucursal Norte")
                                .franchiseId(1L)
                                .build();

                when(franchiseRepository.existsById(1L))
                                .thenReturn(true);

                when(branchRepository.findByFranchiseId(1L))
                                .thenReturn(List.of(branch, second));

                List<Branch> result = branchUseCase.findByFranchise(1L);

                assertEquals(2, result.size());
                assertEquals("Sucursal Centro", result.get(0).getName());
                assertEquals("Sucursal Norte", result.get(1).getName());

                verify(franchiseRepository).existsById(1L);
                verify(branchRepository).findByFranchiseId(1L);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when franchise does not exist")
        void shouldThrowResourceNotFoundExceptionWhenFindingBranchesByFranchise() {

                when(franchiseRepository.existsById(1L))
                                .thenReturn(false);

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> branchUseCase.findByFranchise(1L));

                assertEquals(
                                "Franchise with id 1 not found.",
                                exception.getMessage());

                verify(franchiseRepository).existsById(1L);
                verify(branchRepository, never()).findByFranchiseId(anyLong());
        }
}
