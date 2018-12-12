package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import relationClasses.StorageProduct;
import util.SessionFactoryCfg;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @Test
    void searchProduct() {

        SessionFactory sessionFactory = SessionFactoryCfg.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Storage storage = new Storage("searchTest");
        Product product = new Product("searchTest", 6, BigDecimal.valueOf(500));
        StorageProduct storageProduct = new StorageProduct(storage.getId(), product.getId());

        List<Product> productList = new Search().searchProduct(storage, "searchTest");

        assertEquals(product.toString(), productList.get(0).toString());

        storage.remove();
        session.delete(storageProduct);

        //transaction.commit();
        session.close();

    }
}