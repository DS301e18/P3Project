package model;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import relationClasses.ProductBatch;
import relationClasses.StorageProduct;
import util.SessionFactoryCfg;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void addStorage() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Storage storage = new Storage("Test Lager");

        Storage sessionStorage = session.get(Storage.class, storage.getId());

        assertEquals(storage.getId(), sessionStorage.getId());

        storage.remove();

        session.close();
    }

    @Test
    void addStorageProductRelation() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Storage storage = new Storage("Test Lager");
        Product product = new Product("Test Product", 0, BigDecimal.valueOf(0));

        StorageProduct storageProduct = new StorageProduct(storage.getId(), product.getId());

        StorageProduct sessionStorageProduct = session.get(StorageProduct.class, storageProduct.getId());

        assertEquals(storageProduct.getId(), sessionStorageProduct.getId());

        storage.remove();

        session.close();
    }


    @Test
    void sortProducts() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Storage storage = new Storage("Test Lager");

        Product product1 = new Product("Sprite Test", 0, BigDecimal.valueOf(0));
        Product product2 = new Product("Fanta Test", 0, BigDecimal.valueOf(0));
        Product product3 = new Product("Cola Test", 0, BigDecimal.valueOf(0));

        StorageProduct storageProduct1 = new StorageProduct(storage.getId(), product1.getId());
        StorageProduct storageProduct2 = new StorageProduct(storage.getId(), product2.getId());
        StorageProduct storageProduct3 = new StorageProduct(storage.getId(), product3.getId());

        Storage sessionStorage = session.get(Storage.class, storage.getId());

        assertEquals(storage.sortProducts(), sessionStorage.sortProducts());

        storage.remove();

        session.close();
    }

    @Test
    void calculateTotalPrice() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Storage storage = new Storage("Test Lager");

        Product product = new Product("Sprite Test", 10, BigDecimal.valueOf(10));

        StorageProduct storageProduct = new StorageProduct(storage.getId(), product.getId());

        Batch batch = new Batch(product, "batchNumberTest", 10);

        ProductBatch productBatch = new ProductBatch(product.getId(), batch.getId());

        product.priceOfAllBatches();

        assertEquals(100, storage.calculateTotalPrice().intValue());

        storage.remove();

        session.close();
    }

    @Test
    void toStringTest() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();
        Storage storage = new Storage("Test Lager");

        assertEquals("Test Lager", storage.toString());

        storage.remove();
    }

    @Test
    void updateStorage() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Storage storage = new Storage("Test Lager");

        storage.setName("Lager Test");

        storage.update();

        Storage sessionStorage = session.get(Storage.class, storage.getId());

        assertEquals(storage.getName(), sessionStorage.getName());

        storage.remove();

        session.close();
    }

    @Test
    void removeStorage() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Storage storage = new Storage("Test Lager");

        Storage sessionStorage = session.get(Storage.class, storage.getId());

        storage.remove();

        assertEquals(storage.getId(), sessionStorage.getId());

        session.close();
    }
}