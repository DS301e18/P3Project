package model;

import org.hibernate.HibernateException;
import org.junit.jupiter.api.Test;

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
        LoginCheck test2 = new LoginCheck();
        test2.check("admin", "admin123");
        Employee emp1 = new Employee();
        emp1.setFirstName("Halleluja");
        emp1.setLastName("My god");
        emp1.setPassword("admin123");
        emp1.setRole("Manager");
        emp1.setUsername("admin");
        emp1.setId(9);
        assertEquals(emp1, test2.getEmployee());
    }
}