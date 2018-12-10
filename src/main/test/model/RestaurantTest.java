package model;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import relationClasses.RestaurantEmployee;
import util.SessionFactoryCfg;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantTest {

    @Test
    void addRestaurant() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("TestRestaurant");

        Restaurant sessionRestaurant = session.get(Restaurant.class, restaurant.getId());

        assertEquals(restaurant.getId(), sessionRestaurant.getId());

        restaurant.removeRestaurant();

        session.close();
    }


    @Test
    void relateRestaurantEmployee() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("TestRestaurant");
        Employee employee = new Employee("Test", "Test", "Test", "Test", "Medarbejder");

        RestaurantEmployee restaurantEmployee = new RestaurantEmployee(restaurant.getId(), employee.getId());

        RestaurantEmployee sessionRestaurantEmployee = session.get(RestaurantEmployee.class, restaurantEmployee.getId());

        assertEquals(restaurantEmployee.getId(), sessionRestaurantEmployee.getId());

        restaurant.removeRestaurant();

        session.close();
    }


    @Test
    void sortEmployeesCollectEmployeeRemoveRestaurantRemoveEmployee() {
        SessionFactory sessionFactory = new SessionFactoryCfg().createSessionFactory();
        Session session = sessionFactory.openSession();

        Restaurant restaurant = new Restaurant("ceTest");
        Employee employee = new Employee("app123", "ejh", "ce", "test", "Medarbejder");
        RestaurantEmployee restaurantEmployee = new RestaurantEmployee(restaurant.getId(), employee.getId());

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        assertEquals(employeeList, restaurant.sortEmployees());

        restaurant.removeRestaurant();

        session.close();
    }

}