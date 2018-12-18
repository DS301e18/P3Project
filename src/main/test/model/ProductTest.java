package model;

import org.junit.jupiter.api.Test;
import relationClasses.ProductBatch;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    /**
     * This tests all interesting methods in the product class:
     * <p>
     * collectBatches
     * sortBatches
     * priceOfAllBatches
     **/
    @Test
    void priceOfAllBatches() {
        //Add to database
        Product product = new Product("productTest", 4, BigDecimal.valueOf(200));
        Batch batch = new Batch(product, "qwer1234", 1);

        //Add relation to database
        ProductBatch productBatch = new ProductBatch(product.getId(), batch.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(200, product.priceOfAllBatches().intValue());

        //Removes test objects from database
        product.remove();
    }
}