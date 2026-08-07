package com.franquicias.api.domain.usecases.product;

import com.franquicias.api.domain.exception.DuplicateResourceException;
import com.franquicias.api.domain.exception.ResourceNotFoundException;
import com.franquicias.api.domain.model.Product;
import com.franquicias.api.domain.ports.out.BranchRepositoryPort;
import com.franquicias.api.domain.ports.out.FranchiseRepositoryPort;
import com.franquicias.api.domain.ports.out.ProductRepositoryPort;
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
class ProductUseCaseImplTest {

        @Mock
        private ProductRepositoryPort productRepository;

        @Mock
        private BranchRepositoryPort branchRepository;

        @Mock
        private FranchiseRepositoryPort franchiseRepository;

        @InjectMocks
        private ProductUseCaseImpl productUseCase;

        private Product product;

        @BeforeEach
        void setUp() {

                product = Product.builder()
                                .id(1L)
                                .name("Coca Cola")
                                .stock(100)
                                .branchId(1L)
                                .build();
        }

        @Test
        @DisplayName("Should create product successfully")
        void shouldCreateProductSuccessfully() {

                when(branchRepository.existsById(1L)).thenReturn(true);
                when(productRepository.existsByNameAndBranchId("Coca Cola", 1L)).thenReturn(false);
                when(productRepository.save(product)).thenReturn(product);

                Product result = productUseCase.create(product);

                assertNotNull(result);
                assertEquals("Coca Cola", result.getName());

                verify(branchRepository).existsById(1L);
                verify(productRepository).existsByNameAndBranchId("Coca Cola", 1L);
                verify(productRepository).save(product);
        }

        @Test
        @DisplayName("Should throw exception when branch does not exist")
        void shouldThrowExceptionWhenBranchDoesNotExist() {

                when(branchRepository.existsById(1L)).thenReturn(false);

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> productUseCase.create(product));

                assertEquals(
                                "Branch with id 1 not found.",
                                exception.getMessage());

                verify(productRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw exception when product already exists")
        void shouldThrowExceptionWhenProductAlreadyExists() {

                when(branchRepository.existsById(1L)).thenReturn(true);
                when(productRepository.existsByNameAndBranchId("Coca Cola", 1L)).thenReturn(true);

                DuplicateResourceException exception = assertThrows(
                                DuplicateResourceException.class,
                                () -> productUseCase.create(product));

                assertEquals(
                                "Product 'Coca Cola' already exists in the branch.",
                                exception.getMessage());

                verify(productRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should update stock successfully")
        void shouldUpdateStockSuccessfully() {

                when(productRepository.findById(1L)).thenReturn(Optional.of(product));
                when(productRepository.save(any(Product.class)))
                                .thenAnswer(invocation -> invocation.getArgument(0));

                Product result = productUseCase.updateStock(1L, 250);

                assertEquals(250, result.getStock());

                verify(productRepository).findById(1L);
                verify(productRepository).save(product);
        }

        @Test
        @DisplayName("Should throw exception when updating stock of non existing product")
        void shouldThrowExceptionWhenUpdatingStock() {

                when(productRepository.findById(1L)).thenReturn(Optional.empty());

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> productUseCase.updateStock(1L, 100));

                assertEquals(
                                "Product with id 1 not found.",
                                exception.getMessage());
        }

        @Test
        @DisplayName("Should delete product successfully")
        void shouldDeleteProductSuccessfully() {

                when(productRepository.existsById(1L)).thenReturn(true);

                productUseCase.delete(1L);

                verify(productRepository).deleteById(1L);
        }

        @Test
        @DisplayName("Should throw exception when deleting non existing product")
        void shouldThrowExceptionWhenDeletingProduct() {

                when(productRepository.existsById(1L)).thenReturn(false);

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> productUseCase.delete(1L));

                assertEquals(
                                "Product with id 1 not found.",
                                exception.getMessage());

                verify(productRepository, never()).deleteById(anyLong());
        }

        @Test
        @DisplayName("Should find product by id")
        void shouldFindProductById() {

                when(productRepository.findById(1L)).thenReturn(Optional.of(product));

                Product result = productUseCase.findById(1L);

                assertNotNull(result);
                assertEquals("Coca Cola", result.getName());
        }

        @Test
        @DisplayName("Should throw exception when product does not exist")
        void shouldThrowExceptionWhenFindingProduct() {

                when(productRepository.findById(1L)).thenReturn(Optional.empty());

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> productUseCase.findById(1L));

                assertEquals(
                                "Product with id 1 not found.",
                                exception.getMessage());
        }

        @Test
        @DisplayName("Should find products by branch")
        void shouldFindProductsByBranch() {

                when(branchRepository.existsById(1L)).thenReturn(true);
                when(productRepository.findByBranchId(1L))
                                .thenReturn(List.of(product));

                List<Product> result = productUseCase.findByBranch(1L);

                assertEquals(1, result.size());

                verify(branchRepository).existsById(1L);
                verify(productRepository).findByBranchId(1L);
        }

        @Test
        @DisplayName("Should throw exception when branch does not exist")
        void shouldThrowExceptionWhenFindingProductsByBranch() {

                when(branchRepository.existsById(1L)).thenReturn(false);

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> productUseCase.findByBranch(1L));

                assertEquals(
                                "Branch with id 1 not found.",
                                exception.getMessage());

                verify(productRepository, never()).findByBranchId(anyLong());
        }

        @Test
        @DisplayName("Should return top stock products by franchise")
        void shouldReturnTopStockProductsByFranchise() {

                when(franchiseRepository.existsById(1L)).thenReturn(true);
                when(productRepository.findTopStockProductsByFranchise(1L))
                                .thenReturn(List.of(product));

                List<Product> result = productUseCase.findTopStockProductsByFranchise(1L);

                assertEquals(1, result.size());

                verify(franchiseRepository).existsById(1L);
                verify(productRepository).findTopStockProductsByFranchise(1L);
        }

        @Test
        @DisplayName("Should throw exception when franchise does not exist")
        void shouldThrowExceptionWhenFindingTopStockProducts() {

                when(franchiseRepository.existsById(1L)).thenReturn(false);

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> productUseCase.findTopStockProductsByFranchise(1L));

                assertEquals(
                                "Franchise with id 1 not found.",
                                exception.getMessage());

                verify(productRepository, never())
                                .findTopStockProductsByFranchise(anyLong());
        }

        @Test
        @DisplayName("Should update product name successfully")
        void shouldUpdateProductNameSuccessfully() {

                when(productRepository.findById(1L)).thenReturn(Optional.of(product));
                when(productRepository.existsByNameAndBranchId("Pepsi", 1L)).thenReturn(false);
                when(productRepository.save(any(Product.class)))
                                .thenAnswer(invocation -> invocation.getArgument(0));

                Product result = productUseCase.updateName(1L, "Pepsi");

                assertEquals("Pepsi", result.getName());

                verify(productRepository).save(product);
        }

        @Test
        @DisplayName("Should throw exception when updating duplicated product name")
        void shouldThrowExceptionWhenUpdatingDuplicatedName() {

                when(productRepository.findById(1L)).thenReturn(Optional.of(product));
                when(productRepository.existsByNameAndBranchId("Pepsi", 1L)).thenReturn(true);

                DuplicateResourceException exception = assertThrows(
                                DuplicateResourceException.class,
                                () -> productUseCase.updateName(1L, "Pepsi"));

                assertEquals(
                                "Product 'Pepsi' already exists in the branch.",
                                exception.getMessage());

                verify(productRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should throw exception when updating name of non existing product")
        void shouldThrowExceptionWhenUpdatingNameOfNonExistingProduct() {

                when(productRepository.findById(1L)).thenReturn(Optional.empty());

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> productUseCase.updateName(1L, "Pepsi"));

                assertEquals(
                                "Product with id 1 not found.",
                                exception.getMessage());
        }
}
