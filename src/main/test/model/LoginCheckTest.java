package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import util.SessionFactoryCfg;

import static org.junit.jupiter.api.Assertions.*;

class LoginCheckTest {

    //Tester om funktionen kan finde en bruger i databasen
    @Test
    void check() {
        try{
            LoginCheck test1 = new LoginCheck();
            test1.getSessionFactory();
            boolean result1 = test1.check("admin", "admin123");
            assertEquals(true, result1);
        }
        catch (HibernateException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

    }

    //TODO: insert employee in the test
    //Tester om initializere objekter er indentisk med databaser.
    @Test
    void getEmployee() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Employee employee = new Employee("Testbrugernavn", "test123", "Lille", "Peter", "Medarbejder");
        LoginCheck test2 = new LoginCheck();

        assertTrue(test2.check(employee.getUsername(), employee.getPassword()));

        employee.removeEmployee();

        session.close();
    }
}