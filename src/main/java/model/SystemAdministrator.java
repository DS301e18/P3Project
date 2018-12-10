package model;

import util.AddRemove;
import util.SessionFactoryCfg;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        List<Manager> managerList = new ArrayList<>();

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<Manager> employeeList = session.createQuery("FROM Employee").list();

        for (Manager employee : employeeList) {
            if (employee.getRole().equals("Chef")) {
                managerList.add(employee);
            }
        }

        session.close();
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
