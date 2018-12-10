package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.SessionFactoryCfg;

import java.util.List;

public class LoginCheck {

    private SessionFactory sessionFactory;
    private Employee employee;


    public LoginCheck() {

        sessionFactory = new SessionFactoryCfg().createSessionFactory();
    }

    /** Check if the username equals the corresponding password **/
    public boolean check(String username, String password){

        try (Session session = sessionFactory.openSession()) {
            // Create list of the employees in the database
            Query empQuery = session.createQuery("From Employee where username =:username and password =:password");
            empQuery.setParameter("username", username);
            empQuery.setParameter("password", password);

            Employee foundEmployee = (Employee) empQuery.uniqueResult();

            if (foundEmployee != null) {
                this.employee = foundEmployee;
                return true;
            }

        } catch (HibernateException e) {
            System.out.println("Found either no match or more than more match");
            e.printStackTrace();
            sessionFactory.close();
        }

        return false;
    }

    public Employee getEmployee() {
        return employee;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
