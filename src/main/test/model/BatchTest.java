package model;

import Util.AddRemove;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.From;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BatchTest {

    @Test
    void takeFromBatch() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();


        Batch batchTest = new Batch();
        batchTest.setBatchNumber("qw12");

        try {

            List<Batch> batchList = session.createQuery("FROM Batch ").list();

            for(Batch batch : batchList){
                if (batch.getBatchNumber().equals("qw12")){ assert true;}
            }

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