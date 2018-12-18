package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AddRemove {

    //Add to database
    protected <T> void addObject(T object) {

        SessionFactory sessionFactory = SessionFactoryCfg.getSessionFactory();
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

    //Remove method
    protected <T> void removeObject(T object) {

        SessionFactory sessionFactory = SessionFactoryCfg.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            session.delete(object);

            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Could not delete the object");
            e.printStackTrace();

        } finally {
            session.close();

        }

    }

    //Update method
    protected <T> void updateObject(T object) {

        SessionFactory sessionFactory = SessionFactoryCfg.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            session.update(object);

            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Could not update the object");
            e.printStackTrace();

        } finally {
            session.close();

        }

    }
}
