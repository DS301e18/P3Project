package model;


import org.hibernate.Session;
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
        //Open sessions
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Restaurant restaurant = new Restaurant("TestRestaurant");

        //Creates a new restaurant with the data from the database
        Restaurant sessionRestaurant = session.get(Restaurant.class, restaurant.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(restaurant.getId(), sessionRestaurant.getId());

        //Removes test objects from database
        restaurant.removeRestaurant();

        //Closes the session
        session.close();
    }

    @Test
    void removeRestaurant() {
        //Open sessions
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Restaurant restaurant = new Restaurant("TestRestaurant");

        //Creates a new restaurant with the data from the database
        Restaurant sessionRestaurant = session.get(Restaurant.class, restaurant.getId());

        //Removes test objects from database
        restaurant.removeRestaurant();

        //assertEquals to ensure the expected is happening
        assertEquals(restaurant.getId(), sessionRestaurant.getId());

        //Closes the session
        session.close();
    }

    @Test
    void updateRestaurant() {
        //Open sessions
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Restaurant restaurant = new Restaurant("TestRestaurant");

        //Change name and update
        restaurant.setName("RestaurantTest");
        restaurant.update();

        //Creates a new restaurant with the data from the database
        Restaurant sessionRestaurant = session.get(Restaurant.class, restaurant.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(restaurant.getName(), sessionRestaurant.getName());

        //Removes test objects from database
        restaurant.removeRestaurant();

        //Closes the session
        session.close();
    }

    @Test
    void collectStorages() {
        //Add to database
        Restaurant restaurant = new Restaurant("Test Restaurant");
        Storage storage1 = new Storage("Test Lager1");
        Storage storage2 = new Storage("Test Lager2");
        Storage storage3 = new Storage("Test Lager3");

        //Add relations to database
        RestaurantStorage restaurantStorage1 = new RestaurantStorage(restaurant.getId(), storage1.getId());
        RestaurantStorage restaurantStorage2 = new RestaurantStorage(restaurant.getId(), storage2.getId());
        RestaurantStorage restaurantStorage3 = new RestaurantStorage(restaurant.getId(), storage3.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(3, restaurant.collectStorages().size());

        //Removes test objects from database
        restaurant.removeRestaurant();
    }

    @Test
    void totalPris() {
        //Add to database
        Restaurant restaurant = new Restaurant("Test Restaurant");
        Storage storage = new Storage("Test Lager");
        Product product1 = new Product("Test Product", 10, BigDecimal.valueOf(200));
        Product product2 = new Product("Product Test", 10, BigDecimal.valueOf(200));
        Batch batch2 = new Batch(product2, "Test Batch Number", 5);
        Batch batch1 = new Batch(product1, "Test Batch Number", 5);

        //Add relations to database
        RestaurantStorage restaurantStorage = new RestaurantStorage(restaurant.getId(), storage.getId());
        StorageProduct storageProduct1 = new StorageProduct(storage.getId(), product1.getId());
        StorageProduct storageProduct2 = new StorageProduct(storage.getId(), product2.getId());
        ProductBatch productBatch1 = new ProductBatch(product1.getId(), batch1.getId());
        ProductBatch productBatch2 = new ProductBatch(product2.getId(), batch2.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(2000, storage.calculateTotalPrice().intValue());

        //Removes test objects from database
        restaurant.removeRestaurant();
    }

    @Test
    void sortEmployees() {
        //Add to database
        Restaurant restaurant = new Restaurant("Test Restaurant");
        Employee employee1 = new Employee("Test", "Test", "Johan", "test", "Medarbejder");
        Employee employee2 = new Employee("Test", "Test", "Anders", "test", "Medarbejder");
        Employee employee3 = new Employee("Test", "Test", "Life", "test", "Medarbejder");

        //Add relations to database
        RestaurantEmployee restaurantEmployee1 = new RestaurantEmployee(restaurant.getId(), employee1.getId());
        RestaurantEmployee restaurantEmployee2 = new RestaurantEmployee(restaurant.getId(), employee2.getId());
        RestaurantEmployee restaurantEmployee3 = new RestaurantEmployee(restaurant.getId(), employee3.getId());

        //Manually adding all employees to an arraylist
        ArrayList<Employee> arrayList = new ArrayList<>();
        arrayList.add(employee2);
        arrayList.add(employee1);
        arrayList.add(employee3);

        //assertEquals to ensure the expected is happening
        assertEquals(arrayList, restaurant.sortEmployees());

        //Removes test objects from database
        restaurant.removeRestaurant();
    }

    @Test
    void relateRestaurantEmployee() {
        //Open session
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Add to database
        Restaurant restaurant = new Restaurant("TestRestaurant");
        Employee employee = new Employee("Test", "Test", "Test", "Test", "Medarbejder");

        //Add relation to database
        RestaurantEmployee restaurantEmployee = new RestaurantEmployee(restaurant.getId(), employee.getId());

        //Creates a relation of RestaurantEmployee with the data from the database
        RestaurantEmployee sessionRestaurantEmployee = session.get(RestaurantEmployee.class, restaurantEmployee.getId());

        //assertEquals to ensure the expected is happening
        assertEquals(restaurantEmployee.getId(), sessionRestaurantEmployee.getId());

        //Removes test objects from database
        restaurant.removeRestaurant();

        //Closes the session
        session.close();
    }
}