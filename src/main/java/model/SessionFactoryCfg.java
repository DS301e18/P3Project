package model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryCfg {

    private static SessionFactory sessionFactory;

    /** Create SessionFactory **/
    public SessionFactory createSessionFactory(){
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
            return sessionFactory;
        } catch (Throwable e){
            System.out.println("Failed to create session factory");
            throw new ExceptionInInitializerError(e);
        }
    }

    /** Get current SessionFactory **/
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
