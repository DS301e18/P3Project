package model;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsTest {

    @Test
    void registerTransactionTest() {

        //Instantiates the objects that are input-parameters to the method
        Employee e = new Employee();
        Batch b = new Batch();
        ProductType p = new ProductType();

        //Setting random values for the objects
        e.setFirstname("Kaj");
        p.setName("Pepsi Max");
        p.setBatchCost(23);
        p.setBatchSize(3);
        p.setProductNumber("w573y785");

        b.setBatchNumber("213123");
        b.setProductType(p);
        b.setProductAmount(p.getBatchSize());

        Transactions transactions = new Transactions();
        Transaction transaction;

        transactions.registerTransaction(e, b, 2, "tilføj");

        //Establishing a connection to the database
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        //Assuring that the transaction also is stored in the database
        assertEquals(transactions, session.get(model.Transactions.class, transactions.getId()));
        session.close();
        session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            //Deletes the transaction form the database
            session.delete(transactions);
            transaction.commit();

        } catch (HibernateException ex) {
            System.out.println("Something went wrong");
            ex.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Test
    void hibernateException(){

    }

    @Test
    void getName() {
        Employee employee = new Employee();
        employee.setFirstname("Kaj");
        assertEquals("Kaj", employee.getFirstname());
    }

    @Test
    void getBatch() {
        Batch batch = new Batch();
        batch.setBatchNumber("123KH");
        assertEquals("123KH", batch.getBatchNumber());
    }

    @Test
    void getProduct() {
        ProductType product = new ProductType();
        product.setProductNumber("4321HK");
        assertEquals("4321HK", product.getProductNumber());
    }

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getDate() {
    }

    @Test
    void setDate() {
    }

    @Test
    void getAmount() {
    }

    @Test
    void setAmount() {
    }

    @Test
    void getTranstype() {
    }

    @Test
    void setTranstype() {
    }
}