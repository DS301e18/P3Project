package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemAdministratorTest {

    @Test
    void collectRestaurants() {
        //Add to database
        Restaurant restaurant = new Restaurant("Test Restaurant");

        //assertEquals to ensure the expected is happening
        assertTrue(SystemAdministrator.collectRestaurants().size() >= 1);

        //Removes test objects from database
        restaurant.removeRestaurant();
    }

    @Test
    void collectManagers() {
        //Add to database
        Manager manager = new Manager("Test", "Test", "Test", "Test");

        //assertEquals to ensure the expected is happening
        assertTrue(SystemAdministrator.collectManagers().size() >= 1);

        //Removes test objects from database
        manager.removeEmployee();
    }
}