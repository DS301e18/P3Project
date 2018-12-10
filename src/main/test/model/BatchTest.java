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
            Product product = new Product("testProduct", 2, BigDecimal.valueOf(1000));
            Batch batch = new Batch(product, "test1234",1);

            //batch.takeFromBatch(1);

        } catch (HibernateException e){
            System.out.println("Something went wrong");
        }finally {
            session.close();
        }

    }

    @Test
    void TestAddObjectOnBatch(){
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        SessionFactory sessionFactory = new SessionFactoryCfg().createSessionFactory();
        Product product = new Product("testProduct", 4, BigDecimal.valueOf(200));
        Batch batch = new Batch(product, "test1324",1);

        Batch sessionBatch = session.get(Batch.class, batch.getId());

        assertEquals(batch.getId(), sessionBatch.getId());

        session.close();
    }
}