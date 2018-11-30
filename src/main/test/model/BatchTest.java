package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
}