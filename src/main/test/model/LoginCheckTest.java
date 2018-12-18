package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.SessionFactoryCfg;

import static org.junit.jupiter.api.Assertions.*;

class LoginCheckTest {

    private Employee employee;
    private LoginCheck test;
    private boolean result;

    @BeforeEach
    void before(){
        employee = new Employee("Testbrugernavn", "test123", "test", "test", "Medarbejder");
        test = new LoginCheck();
        result = test.check(employee.getUsername(), employee.getPassword());
    }

    /** Check if the employee can be found */
    @Test
    void check() {
        try{
            assertTrue(result);
        }
        catch (HibernateException e){
            System.out.println("Something went wrong");
            e.printStackTrace();

        } finally {
            employee.removeEmployee();
        }

    }

    /** Check if the employee is the same */
    @Test
    void getEmployee() {

        assertEquals(employee, test.getEmployee());

        employee.removeEmployee();
    }
}