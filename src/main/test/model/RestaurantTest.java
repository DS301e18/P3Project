package model;

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
    void before() {

        sessionFactory = new SessionFactoryCfg().createSessionFactory();

        // opretter nye objekter af Employee og Restaurant
        employee = new Employee();
        restaurant = new Restaurant();
        storage = new Storage();

        employee.setFirstName("Noah");
        employee.setUsername("Noah123");
        employee.setLastName("Herlig");
        employee.setPassword("123");

        //employee.addEmployee();
    }

    /*
    @Test
    void employEmployeeTest() {
        SystemAdministrator sac = new SystemAdministrator();
        sac.addRestaurant("Aalborg");

        sac.getRestaurant().employEmployee(employee);

        AssignedEmployees aecTest = new AssignedEmployees();
        aecTest.setRestaurantId(sac.getRestaurant().getId());
        aecTest.setEmployeeId(employee.getId());

        AssignedEmployees aecDB = new AssignedEmployees();

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        try {
            List<AssignedEmployees> employeeList = session.createQuery("FROM AssignedEmployees").list();
            for (AssignedEmployees aec : employeeList) {
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
*/

    @Test
    void resignEmployeeTest() {

       // restaurant.resignEmployee(employee);
        AssignedEmployees aecDB = null;
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        try {
            List<AssignedEmployees> employeeList = session.createQuery("FROM AssignedEmployees ").list();
            for (AssignedEmployees aec : employeeList) {
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

        assertNull(aecDB);
    }

    //TODO: Use functions already made to add a storage/restaurant etc.
    @Test
    void addStorage() {
        AssignedStorage ascTest = new AssignedStorage();
        ascTest.setStorageId(storage.getId());
        ascTest.setRestaurantId(restaurant.getId());

        //restaurant.addStorage(storage);

        AssignedStorage ascDB = new AssignedStorage();
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        try {
            List<AssignedStorage> storageList = session.createQuery("FROM AssignedStorage ").list();
            for (AssignedStorage asc : storageList) {
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
       // restaurant.removeStorage(storage);
        AssignedStorage assignedStorageDB = null;
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        try {
            List<AssignedStorage> storageList = session.createQuery("FROM AssignedStorage").list();
            for (AssignedStorage asc : storageList) {
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

        assertNull(assignedStorageDB);
    }
}