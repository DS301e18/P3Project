package controller;

import model.Manager;
import model.Restaurant;
import model.SessionFactoryCfg;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SystemAdministratorController {

    /**Fields**/

    private int id;
    private Restaurant restaurant;
    private int restaurantId;
    private Manager manager;
    private int employeeId;
    private String restaurantName;


    /**Methods**/
    public void addManager(Restaurant restaurant, Manager manager){

    }

    public void removeManager(Restaurant restaurant, Manager manager){

    }

    public void addRestaurant(String restaurantName){

        restaurant = new Restaurant();
        restaurant.setName(restaurantName);

        SessionFactory sessionFactory = new SessionFactoryCfg().createSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();
            session.save(restaurant);
            transaction.commit();

        } catch (HibernateException e){
            System.out.println("Could not save the restaurant");
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void removeRestaurant(String restaurantName){

        SessionFactory sessionFactory = new SessionFactoryCfg().createSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction;

        try {
            // Create list of the employees in the database
            List<Restaurant> restaurantList = session.createQuery("FROM Restaurant ").list();

            for (Restaurant restaurant : restaurantList) {
                if(restaurant.getName().equals(restaurantName)){
                    this.restaurant = restaurant;
                    transaction = session.beginTransaction();
                    session.delete(this.restaurant);
                    transaction.commit();
                }
            }

        } catch (HibernateException e){
            System.out.println("Could not delete the restaurant");
            e.printStackTrace();
        } finally {
            session.close();
        }

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
