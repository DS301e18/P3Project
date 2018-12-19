package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.Test;
import util.SessionFactoryCfg;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessionFactoryCfgTest {

    @Test
    public void createSessionFactoryTest(){

        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        session.doWork(connection -> assertTrue(true));
    }

    @Test
    void hibernateException() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        assertThrows(HibernateException.class, () -> sessionFactory.getCurrentSession());
    }

}