package model;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void before(){
        employee = new Employee();

        employee.setUsername("Pommes");
        employee.setPassword("wef7913d");
        employee.setFirstname("Pommes");
        employee.setLastname("Frites");
    }

    @Test
    void addEmployeeTest(){

        employee.addEmployee();

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        assertEquals(employee, session.get(Employee.class, employee.getId()));

        session.close();
    }

    @Test
    void addEmployeeExceptionTest(){
        Employee employeeTest = new Employee();

        assertThrows(IllegalEmployeeException.class, () -> employeeTest.addEmployee());
    }

    @Test
    void removeEmployeeTest(){

        employee.removeEmployee();

        Employee employeeTest = null;

        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        List<Employee> employeeList = session.createQuery("FROM Employee ").list();

        for(Employee emp : employeeList){

            if(emp.equals(employee)){
                employeeTest = emp;
            }
        }

        assertNull(employeeTest);

    }

}