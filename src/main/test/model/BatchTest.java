package model;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import relationClasses.ProductBatch;
import util.SessionFactoryCfg;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BatchTest {

    // Test takeFromBatch()
    @Test
    void takeFromBatch() {
        //Add to database
        Product product = new Product("testProduct", 2, BigDecimal.valueOf(1000));
        Batch batch = new Batch(product, "test1234", 1);

        //Add relation to database
        ProductBatch productBatch = new ProductBatch(product.getId(), batch.getId());

        //Use takeFromBatch method
        batch.takeFromBatch(productBatch, 2);

        //assertEquals to ensure the expected is happening
        assertEquals(0, batch.getRemainingInBox());

        //Removes test objects from database
        product.remove();
    }

    @Test
    void TestAddObjectOnBatch() {
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Product product = new Product("testProduct", 4, BigDecimal.valueOf(200));
        Batch batch = new Batch(product, "test1324", 1);

        //Add relation to database
        Batch sessionBatch = session.get(Batch.class, batch.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(batch.getId(), sessionBatch.getId());

        //Removes test objects from database
        product.remove();

        //Closes the session
        session.close();
    }
}