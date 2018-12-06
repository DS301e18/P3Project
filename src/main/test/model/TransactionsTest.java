package model;

import org.hibernate.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsTest {

    Employee e;
    Batch b;

    @BeforeEach
    void before(){
        //Instantiates the objects that are input-parameters to the method
        b = new Batch();
        Product p = new Product();

        //Setting random values for the objects
        p.setName("Pepsi Max");
        p.setPrice(BigDecimal.valueOf(23));
        p.setBatchSize(3);

        b.setBatchNumber("213123");
        b.setProduct(p);
        b.setRemainingInBox(p.getBatchSize());

    }

    @Test
    void registerTransactionTest() {

        new SessionFactoryCfg().createSessionFactory();

        Transactions transactions = new Transactions();
        Transaction transaction;

        e = new Employee("Kaj", "Kajesen", "KajKajKaj", "1234kaj");

        Restaurant restaurant = new Restaurant("ålleren");

        Storage s = new Storage("Mas");


        new AssignedStorage(restaurant.getId(), s.getId());


        transactions.registerTransaction(s, e, b, 2, "tilføj");

        //Establishing a connection to the database
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        //Assuring that the transaction also is stored in the database
        assertEquals(transactions, session.get(model.Transactions.class, transactions.getId()));
        session.close();

        session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            //Deletes the transaction from the database
            session.delete(transactions);
            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Test
    void getName() {
        Employee employee = new Employee();
        employee.setFirstName("Kaj");
        assertEquals("Kaj", employee.getFirstName());
    }

    @Test
    void getBatch() {
        Batch batch = new Batch();
        batch.setBatchNumber("123KH");
        assertEquals("123KH", batch.getBatchNumber());
    }

    @Test
    void getProduct() {
        Product product = new Product();
        product.setName("Pepsi Max");
        assertEquals("Pepsi Max", product.getName());
    }
}