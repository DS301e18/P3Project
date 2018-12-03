package controller;

import model.Restaurant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemAdministratorControllerTest {

    @Test
    void addManager() {
    }

    @Test
    void removeManager() {
    }

    @Test
    void addRestaurant() {
        SystemAdministratorController admin = new SystemAdministratorController();
        admin.addRestaurant("Mogens");
    }

    @Test
    void removeRestaurant() {
        SystemAdministratorController admin = new SystemAdministratorController();
        admin.removeRestaurant("Mogens");
    }

    @Test
    void getRestaurant() {
    }

    @Test
    void setRestaurant() {
    }

    @Test
    void getManager() {
    }

    @Test
    void setManager() {
    }

    @Test
    void getRestaurantId() {
    }

    @Test
    void setRestaurantId() {
    }

    @Test
    void getEmployeeId() {
    }

    @Test
    void setEmployeeId() {
    }

    @Test
    void getRestaurantName() {
    }

    @Test
    void setRestaurantName() {
    }
}