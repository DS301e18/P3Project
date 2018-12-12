package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import relationClasses.ProductBatch;
import util.SessionFactoryCfg;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

/** This tests all interesting methods in the product class:
 *
 * collectBatches
 * sortBatches
 * priceOfAllBatches
 *
 * **/
    @Test
    void priceOfAllBatches() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Product product = new Product("productTest", 4, BigDecimal.valueOf(200));
        Batch batch = new Batch(product, "qwer1234", 1);
        ProductBatch productBatch = new ProductBatch(product.getId(), batch.getId());

        assertEquals(200, product.priceOfAllBatches().intValue());

        product.remove();

        session.close();
    }
}