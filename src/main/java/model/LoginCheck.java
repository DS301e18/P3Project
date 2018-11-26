package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LoginCheck {

    private SessionFactory sessionFactory;
    private Employee employee;


    public LoginCheck() {
        sessionFactory = new SessionFactoryCfg().createSessionFactory();

    }

    /** Check if the username equals the corresponding password **/
    public boolean check(String username, String password){

        Session session = sessionFactory.openSession();

        try {
            // Create list of the employees in the database
            List<Employee> employeeList = session.createQuery("FROM Employee ").list();

            for (Employee employee : employeeList) {
                if(employee.getUsername().equals(username) && employee.getPassword().equals(password)){
                    this.employee = employee;
                    return true;
                }
            }
        } catch (HibernateException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

        return false;
    }

    public Employee getEmployee() {
        return employee;
    }
}
