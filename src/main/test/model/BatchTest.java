package model;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import relationClasses.ProductBatch;
import util.SessionFactoryCfg;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BatchTest {

    @Test
    void takeFromBatch() {
        Product product = new Product("testProduct", 2, BigDecimal.valueOf(1000));
        Batch batch = new Batch(product, "test1234", 1);

        ProductBatch productBatch = new ProductBatch(product.getId(), batch.getId());

        batch.takeFromBatch(productBatch, 2);

        assertEquals(0, batch.getRemainingInBox());

        product.remove();
    }

    @Test
    void TestAddObjectOnBatch() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();
        Product product = new Product("testProduct", 4, BigDecimal.valueOf(200));
        Batch batch = new Batch(product, "test1324", 1);

        Batch sessionBatch = session.get(Batch.class, batch.getId());
        assertEquals(batch.getId(), sessionBatch.getId());
        product.remove();
        session.close();
    }
}