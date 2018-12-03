package model;

import controller.StorageProductController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StorageTest {
    private ProductType product;
    private Storage storage;

    @BeforeEach
    void before() {
        product = new ProductType();
        storage = new Storage();

        product.setId(1);
        storage.setId(1);
    }

    @Test
    void relateProductToStorage() {
        StorageProductController spcTest = new StorageProductController();
        spcTest.setStorageId(storage.getId());
        spcTest.setProductId(product.getId());

        storage.relateProductToStorage(product);

        StorageProductController spcDB = new StorageProductController();
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            List<StorageProductController> storageProductList = session.createQuery("FROM StorageProductController").list();

            for (StorageProductController storageProducts : storageProductList) {
                if (storageProducts.getProductId() == product.getId()) {
                    spcDB = storageProducts;
                }
            }
        } catch (HibernateException e) {
            System.out.println("Couldn't create a relation");
            e.printStackTrace();
        } finally {
            session.close();
        }
        assertEquals(spcTest, spcDB);
    }

    @Test
    void unrelateProductFromStorage() {
        storage.unrelateProductFromStorage(product);
        StorageProductController spcDB = null;
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            List<StorageProductController> storageProductList = session.createQuery("FROM StorageProductController").list();

            for (StorageProductController storageProducts : storageProductList) {
                if (storageProducts.getProductId() == product.getId()) {
                    spcDB = storageProducts;
                }
            }

        } catch (HibernateException e) {
            System.out.println("Couldn't unrelate this product from storage");
            e.printStackTrace();
        } finally {
            session.close();
        }

        assertNull(spcDB);

    }
}