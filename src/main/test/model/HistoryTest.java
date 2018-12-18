package model;

import org.junit.jupiter.api.Test;
import util.AddRemove;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest extends AddRemove {

    @Test
    void readEmployeeHistory() {
        //Add to database
        History history = new History();
        Transactions transactions = new Transactions();

        Restaurant restaurant = new Restaurant("Test Restaurant");
        Employee employee = new Employee("Test12", "Test12", "Tester", "Testersen", "Medarbejder");
        Storage storage = new Storage("Test Lager");
        Product product = new Product("Product Test", 10, BigDecimal.valueOf(10));
        Batch batch = new Batch(product, "batchNumberTest", 10);

        transactions.registerTransaction(storage, employee, batch, 2, "tilføj");
        //assertEquals to ensure the expected is happening
        assertTrue(history.readEmployeeHistory(employee.getFirstName() + " " + employee.getLastName()).size() >= 1);

        //Removes test objects from database
        restaurant.removeRestaurant();
    }

    @Test
    void readHistory() {
        //Add to database
        History history = new History();
        Transactions transactions = new Transactions();

        Restaurant restaurant = new Restaurant("Test Restaurant");
        Employee employee = new Employee("Test12", "Test12", "Tester", "Testersen", "Medarbejder");
        Storage storage = new Storage("Test Lager");
        Product product = new Product("Product Test", 10, BigDecimal.valueOf(10));
        Batch batch = new Batch(product, "batchNumberTest", 10);

        transactions.registerTransaction(storage, employee, batch, 2, "tilføj");

        //assertEquals to ensure the expected is happening
        assertTrue(history.readHistory(1, employee.getFirstName() + " " + employee.getLastName(), storage).size() >= 1);

        //Removes test objects from database
        restaurant.removeRestaurant();
    }
}