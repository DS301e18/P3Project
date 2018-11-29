package model;

import com.fasterxml.classmate.AnnotationConfiguration;
import controller.AssignedEmployeesController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Employee employee;
    private Restaurant restaurant;

    @BeforeEach
    public void before() {

        // opretter nye objekter af Employee og Restaurant
        employee = new Employee();
        restaurant = new Restaurant();

        // hardcoder værdier af employee og restaurant
        employee.setId(1);
        restaurant.setId(1);
    }

    @Test
    void employEmployeeTest() {

        AssignedEmployeesController aecTest = new AssignedEmployeesController();
        aecTest.setEmployeeId(employee.getId());
        aecTest.setRestaurantId(restaurant.getId());

        restaurant.employEmployee(employee);

        AssignedEmployeesController aecDB = new AssignedEmployeesController();
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            List<AssignedEmployeesController> employeeList = session.createQuery("FROM AssignedEmployeesController ").list();
            for (AssignedEmployeesController aec : employeeList) {
                if (aec.getEmployeeId() == employee.getId()) {
                    aecDB = aec;
                }
            }

        } catch (HibernateException e) {
            System.out.println("nothing to resign");
            e.printStackTrace();
        } finally {
            session.close();
        }
        assertEquals(aecTest, aecDB);

    }


    @Test
    void resignEmployeeTest() {


    }
}