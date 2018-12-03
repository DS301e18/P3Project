package model;

import controller.SystemAdministratorController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeExample {

    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();

        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            Employee employee = new Employee();
            employee.setUsername("admin");
            employee.setPassword("admin123");
            employee.setFirstname("Halleluja");
            employee.setLastname("My god");
            employee.setRole("Chef");
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e){
            System.out.println("Could not save the object");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
