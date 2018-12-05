package model;


import Util.AddRemove;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Employee employee;
    private Restaurant restaurant;
    private Storage storage;


    @Test
    void addRestaurant() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("TestRestaurant");

        Restaurant sessionRestaurant = session.get(Restaurant.class, restaurant.getId());

        assertEquals(restaurant.getId(), sessionRestaurant.getId());

        session.close();
    }


    @Test
    void removeRestaurant() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("TestRestaurant");
    }


    @Test
    void relateRestaurantEmployee() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("TestRestaurant");
        Employee employee = new Employee("Test", "Test", "Test", "Test");

        AssignedEmployees assignedEmployees = new AssignedEmployees(restaurant.getId(), employee.getId());

        AssignedEmployees sessionAssignedEmployees = session.get(AssignedEmployees.class, assignedEmployees.getId());

        assertEquals(assignedEmployees.getId(), sessionAssignedEmployees.getId());

        session.close();

    }



    /*
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
    }*/
}