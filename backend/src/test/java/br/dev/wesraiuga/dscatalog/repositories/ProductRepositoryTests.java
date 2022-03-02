package br.dev.wesraiuga.dscatalog.repositories;

import br.dev.wesraiuga.dscatalog.entities.Product;
import br.dev.wesraiuga.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static br.dev.wesraiuga.dscatalog.tests.Factory.createProduct;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }

    @Test
    public void save_shouldPersistWithAutoincrement_whenIdIsNull() {
        Product product = createProduct();
        product.setId(null);

        product = repository.save(product);

        assertNotNull(product.getId());
        assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void delete_shouldDeleteObject_whenIdExists() {
        repository.deleteById(existingId);

        Optional<Product> result = repository.findById(existingId);

        assertFalse(result.isPresent());
    }

    @Test
    public void delete_shouldThrowEmptyResultDataAccessException_whenIdDoesNotExist() {
        Assertions.assertThrows(
                EmptyResultDataAccessException.class,
                () -> repository.deleteById(nonExistingId)
        );
    }

}
