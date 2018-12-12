package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.SessionFactoryCfg;

import java.util.List;

public class LoginCheck {
    private Employee employee;


    public LoginCheck() {}

    /** Check if the username equals the corresponding password **/
    public boolean check(String username, String password){

        try (Session session = SessionFactoryCfg.getSessionFactory().openSession()) {
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
        }

        return false;
    }

    public Employee getEmployee() {
        return employee;
    }
}
