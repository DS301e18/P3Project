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
            Employee foundEmployee = (Employee) empQuery.list().get(0);

            if (foundEmployee.getUsername().equals(username) && foundEmployee.getPassword().equals(password)) {
                this.employee = foundEmployee;
                return true;
            }

            /*List<Employee> employeeList = session.createQuery("FROM Employee ").list();

            for (Employee employee : employeeList) {
                if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                    this.employee = employee;
                    return true;
                }
            }*/

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
