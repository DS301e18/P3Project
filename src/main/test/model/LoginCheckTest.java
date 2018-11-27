package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginCheckTest {

    //Tester om funktionen kan finde en bruger i databasen
    @Test
    void check() {
        LoginCheck test1 = new LoginCheck();
        boolean result1 = test1.check("admin", "admin123");
        assertEquals(true, result1);

    }

    //Fungere ikkte p.t
    @Test
    void getEmployee() {
        LoginCheck test2 = new LoginCheck();
        Employee Emp1 = new Employee();
        test2.getEmployee();
        assertEquals(Emp1, test2.getEmployee());


    }
}