package model;

import org.hibernate.*;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import relationClasses.ProductBatch;
import relationClasses.RestaurantEmployee;
import relationClasses.RestaurantStorage;
import relationClasses.StorageProduct;
import util.AddRemove;
import util.SessionFactoryCfg;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsTest extends AddRemove {

    @Test
    void registerTransactionTest() {
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //New transactions
        Transactions transactions = new Transactions();

        //Add to database
        Restaurant restaurant = new Restaurant("Test Restaurant");
        Employee employee = new Employee("Test12", "Test12", "Test Person", "Test Name", "Medarbejder");
        Storage storage = new Storage("Test Lager");
        Product product = new Product("Product Test", 10, BigDecimal.valueOf(10));
        Batch batch = new Batch(product, "batchNumberTest", 10);

        //Add relations to database
        RestaurantStorage restaurantStorage = new RestaurantStorage(restaurant.getId(), storage.getId());
        RestaurantEmployee restaurantEmployee = new RestaurantEmployee(restaurant.getId(), employee.getId());
        StorageProduct storageProduct = new StorageProduct(storage.getId(), product.getId());
        ProductBatch productBatch = new ProductBatch(product.getId(), batch.getId());

        //Makes transaction
        transactions.registerTransaction(storage, employee, batch, 2, "tilføj");

        //Query for this unique transaction and saving it as foundTransaction
        Query transQuery = session.createQuery("From Transactions where id =:id");
        transQuery.setParameter("id", transactions.getId());
        Transactions foundTransactions = (Transactions) transQuery.uniqueResult();

        //assertEquals to ensure the expected is happening
        assertEquals(transactions.getId(), foundTransactions.getId());

        //Removes test objects from database
        restaurant.removeRestaurant();

        //Closes the session
        session.close();
    }
}