package controller;

import model.Restaurant;
import model.SessionFactoryCfg;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        //Establishing a connection to the database
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        SystemAdministratorController admin = new SystemAdministratorController();
        admin.addRestaurant("Mogens");

        Restaurant restaurant = session.get(Restaurant.class, admin.getRestaurantId());

        assertEquals("Mogens", restaurant.getName());

        //Assuring that the transaction also is stored in the database
        session.close();

        /*
        session = new SessionFactoryCfg().createSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            //Deletes the restaurant form the database
            session.delete(restaurant);
            transaction.commit();

        } catch (HibernateException ex) {
            System.out.println("The restaurant couldn't be removed");
            ex.printStackTrace();
        } finally {
            session.close();
        }*/
    }

    @Test
    void removeRestaurant() {
        //Establishing a connection to the database
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        SystemAdministratorController admin = new SystemAdministratorController();
        admin.addRestaurant("Mogens");
        admin.removeRestaurant("Mogens");

        //Creating an empty restaurant
        Restaurant empty = null;

        List<Restaurant> restaurantList = session.createQuery("FROM Restaurant").list();

        for (Restaurant restaurant : restaurantList) {
            //If the restaurant that was mend to be deleted is found in the restaurant table, set the empty restaurant to be that
            if (restaurant.equals(admin.getRestaurant())) {
                empty = restaurant;
            }
        }

        assertNull(empty);

    }

}