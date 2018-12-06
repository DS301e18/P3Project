package model;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import relationClasses.StorageProduct;
import util.SessionFactoryCfg;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void addStorage() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Storage storage = new Storage("Test Lager");

        Storage sessionStorage = session.get(Storage.class, storage.getId());

        assertEquals(storage.getId(), sessionStorage.getId());

        session.close();
    }

    @Test
    void addStorageProductRelation() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Storage storage = new Storage("Test Lager");
        Product product = new Product("Test Product", 0, BigDecimal.valueOf(0));

        StorageProduct storageProduct = new StorageProduct(storage.getId(), product.getId());

        StorageProduct sessionStorageProduct = session.get(StorageProduct.class, storageProduct.getId());

        assertEquals(storageProduct.getId(), sessionStorageProduct.getId());

        session.close();
    }

    @Test
    void removeStorage(){

    }

    @Test
    void sortProducts() {
    }

    @Test
    void calculateTotalPrice() {
    }
}