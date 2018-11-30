package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

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

}