package ua.com.company.inventorysystem.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.company.inventorysystem.model.Product;
import ua.com.company.inventorysystem.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(1L, "Product1", "Description1", 10.0, 100);
        Product product2 = new Product(2L, "Product2", "Description2", 20.0, 200);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        Product product = new Product(1L, "Product1", "Description1", 10.0, 100);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals("Product1", foundProduct.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Product> foundProduct = productService.getProductById(1L);

        assertFalse(foundProduct.isPresent());
        verify(productRepository, times(1)).findById(1L);
    }
    @Test
    void testAddProduct() {
        Product product = new Product(null, "Product1", "Description1", 10.0, 100);

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.addProduct(product);

        assertNotNull(savedProduct);
        assertEquals("Product1", savedProduct.getName());
        verify(productRepository, times(1)).save(product);
    }
    @Test
    void testUpdateProduct() {
        Product existingProduct = new Product(1L, "Product1", "Description1", 10.0, 100);
        Product updatedProduct = new Product(1L, "UpdatedProduct", "UpdatedDescription", 15.0, 150);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(1L, updatedProduct);

        assertNotNull(result);
        assertEquals("UpdatedProduct", result.getName());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(updatedProduct);
    }

    @Test
    void testUpdateProductNotFound() {
        Product updatedProduct = new Product(1L, "UpdatedProduct", "UpdatedDescription", 15.0, 150);

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(1L, updatedProduct);
        });

        assertEquals("Product not found", exception.getMessage());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(0)).save(updatedProduct);
    }
    @Test
    void testDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProductNotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.deleteProduct(1L);
        });

        assertEquals("Product not found", exception.getMessage());
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(0)).deleteById(1L);
    }


}