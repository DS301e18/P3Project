package model;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import relationClasses.StorageProduct;
import util.SessionFactoryCfg;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    @Test
    void searchProduct() {
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Storage storage = new Storage("searchTest");
        Product product = new Product("searchTest", 6, BigDecimal.valueOf(500));

        //Add relation to database
        StorageProduct storageProduct = new StorageProduct(storage.getId(), product.getId());

        //New list of product in test storage
        List<Product> productList = new Search().searchProduct(storage, "searchTest");

        //assertEquals to ensure the expected is happening
        assertEquals(product.getId(), productList.get(0).getId());

        //Removes test objects from database
        storage.remove();

        //Closes the session
        session.close();
    }
}