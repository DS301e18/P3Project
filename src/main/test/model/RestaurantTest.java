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
        //Employee employee = new Employee("Test", "Test", "Test", "Test");

        RestaurantEmployee restaurantEmployee = new RestaurantEmployee(restaurant.getId(), employee.getId());

        RestaurantEmployee sessionRestaurantEmployee = session.get(RestaurantEmployee.class, restaurantEmployee.getId());

        assertEquals(restaurantEmployee.getId(), sessionRestaurantEmployee.getId());

        session.close();
    }


    @Test
    void sortEmployeesANDcollctEmployee(){
        SessionFactory sessionFactory = new SessionFactoryCfg().createSessionFactory();

        Restaurant restaurant = new Restaurant("ceTest");
        Employee employee = new Employee("app123", "ejh", "ce", "test");
        new RestaurantEmployee(restaurant.getId(), employee.getId());

        List<Employee> EmployeeList = new ArrayList<>();
        EmployeeList.add(employee);

        assertEquals(EmployeeList, restaurant.sortEmployees());

    }

}