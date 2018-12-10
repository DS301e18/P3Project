package model;

import org.hibernate.*;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import relationClasses.ProductBatch;
import relationClasses.RestaurantEmployee;
import relationClasses.RestaurantStorage;
import relationClasses.StorageProduct;
import util.SessionFactoryCfg;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsTest {
    Session session = new SessionFactoryCfg().createSessionFactory().openSession();

    @Test
    void registerTransactionTest() {
        session.getSessionFactory();

        Transactions transactions = new Transactions();

        Restaurant restaurant = new Restaurant("Test Restaurant");
        Employee employee = new Employee("Test12", "Test12", "Test Person", "Test Name", "Medarbejder");
        Storage storage = new Storage("Test Lager");
        Product product = new Product("Product Test", 10, BigDecimal.valueOf(10));
        Batch batch = new Batch(product, "batchNumberTest", 10);

        RestaurantStorage restaurantStorage = new RestaurantStorage(restaurant.getId(), storage.getId());
        RestaurantEmployee restaurantEmployee = new RestaurantEmployee(restaurant.getId(), employee.getId());
        StorageProduct storageProduct = new StorageProduct(storage.getId(), product.getId());
        ProductBatch productBatch = new ProductBatch(product.getId(), batch.getId());

        transactions.registerTransaction(storage, employee, batch, 2, "tilføj");

        Query transQuery = session.createQuery("From Transactions where id =:id");

        transQuery.setParameter("id", transactions.getId());

        Transactions foundTransactions = (Transactions) transQuery.uniqueResult();

        assertTrue(foundTransactions != null);

        restaurant.removeRestaurant();

        session.close();
    }
}