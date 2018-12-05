package Util;

import model.SessionFactoryCfg;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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


    protected  <T> void removeObject(T object) {

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

    protected <T> List collectObject(T object, String string, int objectId){

        SessionFactory sessionFactory = new SessionFactoryCfg().getSessionFactory();
        Session session = sessionFactory.openSession();

        List<T> objectCollection = new ArrayList<>();

        try {
            List<T> objectList = session.createQuery(string).list();
            for (T objectType: objectList){
                Field field = objectType.getClass().getDeclaredField("id");

                if(objectType.equals(field)){
                    objectCollection.add(objectType);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();

        } catch (HibernateException e){
            System.out.println("Something went wrong with Hibernate");

        } return objectCollection;
    }
}
