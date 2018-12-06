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

class SessionFactoryCfgTest {

    @Test
    public void createSessionFactoryTest(){

        new SessionFactoryCfg().createSessionFactory();

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        session.doWork(new Work() {
            public void execute(Connection connection) throws SQLException {

            }
        });
    }

    @Test
    void hibernateException() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        assertThrows(HibernateException.class, () -> sessionFactory.getCurrentSession());
    }

}