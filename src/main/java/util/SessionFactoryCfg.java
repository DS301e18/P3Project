package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryCfg {

    private static SessionFactory sessionFactory;

    private SessionFactoryCfg(){}

    public static SessionFactory getSessionFactory() {

        //If sessionFactory hasn't been made yet, make it from the cfg.xml file
        if(sessionFactory == null){
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        return sessionFactory;
    }
}
