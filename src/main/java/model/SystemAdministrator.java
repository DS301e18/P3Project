package model;

import org.hibernate.*;
import util.AddRemove;
import util.SessionFactoryCfg;

import java.util.ArrayList;
import java.util.List;

public class SystemAdministrator extends AddRemove {

    /**
     * Fields
     **/

    private int id;
    private Restaurant restaurant;
    private int restaurantId;
    private Manager manager;
    private int employeeId;
    private String restaurantName;


    /**
     * Methods
     **/
    public static List<Restaurant> collectRestaurants() {
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();
        List<Restaurant> restaurantList = session.createQuery("FROM Restaurant").list();
        session.close();
        return restaurantList;
    }

    public static List<Manager> collectManagers() {
        List<Manager> managerList;

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        Query employeeQuery = session.createQuery("FROM Employee where role=:i");
        employeeQuery.setParameter("i", "Superbruger");

        managerList = employeeQuery.list();

        session.close();

        System.out.println(managerList);
        return managerList;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int getRestaurantId() {
        return restaurant.getId();
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
