package Util;

import model.SessionFactoryCfg;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AddRemove {

    protected <T> void addObject(T object) {

        SessionFactory sessionFactory = new SessionFactoryCfg().getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            session.save(object);

            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Could not save the object");
            e.printStackTrace();

        } finally {
            session.close();

        }
    }

    private <T> void removeObject(T object) {

        SessionFactory sessionFactory = new SessionFactoryCfg().getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            session.delete(object);

            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Could not save the object");
            e.printStackTrace();

        } finally {
            session.close();

        }

    }
}
