package model;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import relationClasses.ProductBatch;
import relationClasses.RestaurantEmployee;
import relationClasses.RestaurantStorage;
import relationClasses.StorageProduct;
import util.SessionFactoryCfg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantTest {

    @Test
    void addRestaurant() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("TestRestaurant");

        Restaurant sessionRestaurant = session.get(Restaurant.class, restaurant.getId());

        assertEquals(restaurant.getId(), sessionRestaurant.getId());

        restaurant.removeRestaurant();

        session.close();
    }

    @Test
    void removeRestaurant() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("TestRestaurant");

        Restaurant sessionRestaurant = session.get(Restaurant.class, restaurant.getId());

        restaurant.removeRestaurant();

        assertEquals(restaurant.getId(), sessionRestaurant.getId());

        session.close();
    }

    @Test
    void updateRestaurant() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("TestRestaurant");
        restaurant.setName("RestaurantTest");
        restaurant.update();

        Restaurant sessionRestaurant = session.get(Restaurant.class, restaurant.getId());

        assertEquals(restaurant.getName(), sessionRestaurant.getName());

        restaurant.removeRestaurant();

        session.close();
    }

    @Test
    void collectStorages() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("Test Restaurant");

        Storage storage1 = new Storage("Test Lager1");
        Storage storage2 = new Storage("Test Lager2");
        Storage storage3 = new Storage("Test Lager3");

        RestaurantStorage restaurantStorage1 = new RestaurantStorage(restaurant.getId(), storage1.getId());
        RestaurantStorage restaurantStorage2 = new RestaurantStorage(restaurant.getId(), storage2.getId());
        RestaurantStorage restaurantStorage3 = new RestaurantStorage(restaurant.getId(), storage3.getId());

        assertEquals(3, restaurant.collectStorages().size());

        restaurant.removeRestaurant();

        session.close();
    }

    @Test
    void totalPris() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("Test Restaurant");

        Storage storage = new Storage("Test Lager");

        RestaurantStorage restaurantStorage = new RestaurantStorage(restaurant.getId(), storage.getId());

        Product product1 = new Product("Test Product",10, BigDecimal.valueOf(200));
        Product product2 = new Product("Product Test", 10, BigDecimal.valueOf(200));

        StorageProduct storageProduct1 = new StorageProduct(storage.getId(), product1.getId());
        StorageProduct storageProduct2 = new StorageProduct(storage.getId(), product2.getId());

        Batch batch1 = new Batch(product1, "Test Batch Number", 5);
        Batch batch2 = new Batch(product2, "Test Batch Number", 5);

        ProductBatch productBatch1 = new ProductBatch(product1.getId(), batch1.getId());
        ProductBatch productBatch2 = new ProductBatch(product2.getId(), batch2.getId());

        assertEquals(2000, storage.calculateTotalPrice().intValue());

        restaurant.removeRestaurant();

        session.close();
    }

    @Test
    void sortEmployees() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Restaurant restaurant = new Restaurant("Test Restaurant");

        Employee employee1 = new Employee("Test", "Test", "Johan", "test", "Medarbejder");
        Employee employee2 = new Employee("Test", "Test", "Anders", "test", "Medarbejder");
        Employee employee3 = new Employee("Test", "Test", "Life", "test", "Medarbejder");

        RestaurantEmployee restaurantEmployee1 = new RestaurantEmployee(restaurant.getId(), employee1.getId());
        RestaurantEmployee restaurantEmployee2 = new RestaurantEmployee(restaurant.getId(), employee2.getId());
        RestaurantEmployee restaurantEmployee3 = new RestaurantEmployee(restaurant.getId(), employee3.getId());

        ArrayList<Employee> arrayList = new ArrayList<>();
        arrayList.add(employee2);
        arrayList.add(employee1);
        arrayList.add(employee3);

        assertEquals(arrayList, restaurant.sortEmployees());

        restaurant.removeRestaurant();

        session.close();
    }


    @Test
    void relateRestaurantEmployee() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

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
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

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