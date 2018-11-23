package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class LoginCheck {

    public boolean check(String username, String password){

        SessionFactory sessionFactory;

        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e){
            System.out.println("Failed to create session factory");
            throw new ExceptionInInitializerError(e);
        }

        Session session = sessionFactory.openSession();

        try {
            List<Employee> employeeList = session.createQuery("FROM Employee ").list();

            for (Employee employee : employeeList) {
                if(employee.getUsername().equals(username)){
                    if(employee.getPassword().equals(password)){
                        return true;
                    }
                }
            }
        } catch (HibernateException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

        return false;
    }
}
