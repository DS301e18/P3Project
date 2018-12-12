package model;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import util.SessionFactoryCfg;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;

    @Test
    void addEmployeeTest(){
            Session session = SessionFactoryCfg.getSessionFactory().openSession();

            Employee employee = new Employee("Test","Test","Test","Test","Medarbejder");

            Employee sessionEmployee = session.get(Employee.class, employee.getId());

            assertEquals(employee.getId(), sessionEmployee.getId());

            employee.removeEmployee();

            session.close();
        }

    @Test
    void addEmployeeExceptionTest(){
        SessionFactoryCfg.getSessionFactory().openSession();
        //new Employee("Test", "test", "Jørgen", "Åge");

        //assertThrows(IllegalEmployeeException.class, () -> employeeTest.addEmployee());
    }


    @Test
    void removeEmployeeTest(){

        //employee.removeEmployee();

        Employee employeeTest = null;

        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        List<Employee> employeeList = session.createQuery("FROM Employee").list();

        for(Employee emp : employeeList){

            if(emp.equals(employee)){
                employeeTest = emp;
            }
        }

        assertNull(employeeTest);

    }

}