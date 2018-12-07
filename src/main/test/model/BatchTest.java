package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import util.SessionFactoryCfg;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BatchTest {

    @Test
    void takeFromBatch() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            Product product = new Product("plzvirk", 2, BigDecimal.valueOf(1000));
            Batch batch = new Batch(product, "ass123");

            //batch.takeFromBatch(1);

        } catch (HibernateException e){
            System.out.println("PIS");
        }finally {
            session.close();
        }

    }

    @Test
    void TestAddObjectOnBatch(){
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        SessionFactory sessionFactory = new SessionFactoryCfg().createSessionFactory();
        Product product = new Product("TestProduct", 4, BigDecimal.valueOf(200));
        Batch batch = new Batch(product, "test1324");

        Batch sessionBatch = session.get(Batch.class, batch.getId());

        assertEquals(batch.getId(), sessionBatch.getId());

        session.close();
    }
}