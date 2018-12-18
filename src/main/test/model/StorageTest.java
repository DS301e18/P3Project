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
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Storage storage = new Storage("Test Lager");

        //Creates a storage with test data from the database
        Storage sessionStorage = session.get(Storage.class, storage.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(storage.getId(), sessionStorage.getId());

        //Removes test objects from database
        storage.remove();

        //Closes the session
        session.close();
    }

    @Test
    void addStorageProductRelation() {
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Storage storage = new Storage("Test Lager");
        Product product = new Product("Test Product", 0, BigDecimal.valueOf(0));

        //Add relation to database
        StorageProduct storageProduct = new StorageProduct(storage.getId(), product.getId());

        //Creates a storage with test data from the database
        StorageProduct sessionStorageProduct = session.get(StorageProduct.class, storageProduct.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(storageProduct.getId(), sessionStorageProduct.getId());

        //Removes test objects from database
        storage.remove();

        //Closes the session
        session.close();
    }


    @Test
    void sortProducts() {
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Storage storage = new Storage("Test Lager");
        Product product1 = new Product("Sprite Test", 0, BigDecimal.valueOf(0));
        Product product2 = new Product("Fanta Test", 0, BigDecimal.valueOf(0));
        Product product3 = new Product("Cola Test", 0, BigDecimal.valueOf(0));

        //Add relation to database
        StorageProduct storageProduct1 = new StorageProduct(storage.getId(), product1.getId());
        StorageProduct storageProduct2 = new StorageProduct(storage.getId(), product2.getId());
        StorageProduct storageProduct3 = new StorageProduct(storage.getId(), product3.getId());

        //Creates a storage with test data from the database
        Storage sessionStorage = session.get(Storage.class, storage.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(storage.sortProducts(), sessionStorage.sortProducts());

        //Removes test objects from database
        storage.remove();

        //Closes the session
        session.close();
    }

    @Test
    void calculateTotalPrice() {
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Storage storage = new Storage("Test Lager");
        Product product = new Product("Sprite Test", 10, BigDecimal.valueOf(10));
        Batch batch = new Batch(product, "batchNumberTest", 10);

        //Add relation to database
        StorageProduct storageProduct = new StorageProduct(storage.getId(), product.getId());
        ProductBatch productBatch = new ProductBatch(product.getId(), batch.getId());

        //Method call
        product.priceOfAllBatches();

        //assertEquals to ensure the expected is happening
        assertEquals(100, storage.calculateTotalPrice().intValue());

        //Removes test objects from database
        storage.remove();

        //Closes the session
        session.close();
    }

    @Test
    void updateStorage() {
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Storage storage = new Storage("Test Lager");

        //Change name
        storage.setName("Lager Test");

        //Update name to the database
        storage.update();

        //Creates a storage with test data from the database
        Storage sessionStorage = session.get(Storage.class, storage.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(storage.getName(), sessionStorage.getName());

        //Removes test objects from database
        storage.remove();

        //Closes the session
        session.close();
    }

    @Test
    void removeStorage() {
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Storage storage = new Storage("Test Lager");

        //Creates a storage with test data from the database
        Storage sessionStorage = session.get(Storage.class, storage.getId());

        //Removes test objects from database
        storage.remove();

        //assertEquals to ensure the expected is happening
        assertEquals(storage.getId(), sessionStorage.getId());

        //Closes the session
        session.close();
    }
}