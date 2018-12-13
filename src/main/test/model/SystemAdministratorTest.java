package model;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import util.SessionFactoryCfg;

import static org.junit.jupiter.api.Assertions.*;

class SystemAdministratorTest {

    @Test
    void collectRestaurants() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("Test Restaurant");

        assertTrue(SystemAdministrator.collectRestaurants().size() >= 1);

        restaurant.removeRestaurant();

        session.close();
    }

    @Test
    void collectManagers() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Manager manager = new Manager("Test", "Test", "Test", "Test");

        assertTrue(SystemAdministrator.collectManagers().size() >= 1);

        manager.removeEmployee();

        session.close();
    }
}