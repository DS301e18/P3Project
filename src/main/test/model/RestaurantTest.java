package model;

import controller.AssignedEmployeesController;
import controller.AssignedStorageController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Employee employee;
    private Restaurant restaurant;
    private Storage storage;

    @BeforeEach
    public void before() {

        // opretter nye objekter af Employee og Restaurant
        employee = new Employee();
        restaurant = new Restaurant();
        storage = new Storage();

        // hardcoder værdier af employee og restaurant
        employee.setId(1);
        restaurant.setId(1);
        storage.setId(1);
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
            List<AssignedEmployeesController> employeeList = session.createQuery("FROM AssignedEmployeesController").list();
            for (AssignedEmployeesController aec : employeeList) {
                if (aec.getEmployeeId() == employee.getId()) {
                    aecDB = aec;
                }
            }

        } catch (HibernateException e) {
            System.out.println("nothing to assign");
            e.printStackTrace();
        } finally {
            session.close();
        }
        assertEquals(aecTest, aecDB);

    }


    @Test
    void resignEmployeeTest() {

        restaurant.resignEmployee(employee);
        AssignedEmployeesController aecDB = null;
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            List<AssignedEmployeesController> employeeList = session.createQuery("FROM AssignedEmployeesController ").list();
            for (AssignedEmployeesController aec : employeeList) {
                if (aec.getEmployeeId() == employee.getId()) {
                    aecDB = aec;
                }
            }
        } catch (HibernateException e) {
            System.out.println("could not resign employee");
            e.printStackTrace();
        } finally {
            session.close();
        }
        assertTrue(aecDB == null);
    }

    @Test
    void addStorage() {
        AssignedStorageController ascTest = new AssignedStorageController();
        ascTest.setStorageId(storage.getId());
        ascTest.setRestaurantId(restaurant.getId());

        restaurant.addStorage(storage);

        AssignedStorageController ascDB = new AssignedStorageController();
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            List<AssignedStorageController> storageList = session.createQuery("FROM AssignedStorageController ").list();
            for (AssignedStorageController asc : storageList) {
                if (asc.getStorageId() == storage.getId()) ;
                ascDB = asc;
            }

        } catch (HibernateException e) {
            System.out.println("could not add storage");
            e.printStackTrace();
        } finally {
            session.close();
        }
        assertEquals(ascTest, ascDB);

    }

    @Test
    void removeStorage() {
        restaurant.removeStorage(storage);
        AssignedStorageController assignedStorageDB = null;
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            List<AssignedStorageController> storageList = session.createQuery("FROM AssignedStorageController ").list();
            for (AssignedStorageController asc : storageList) {
                if (asc.getStorageId() == storage.getId()) {
                    assignedStorageDB = asc;
                }
            }
        } catch (HibernateException e) {
            System.out.println("no storage to remove");
            e.printStackTrace();
        } finally {
            session.close();
        }
        assertTrue(assignedStorageDB == null);
    }
}