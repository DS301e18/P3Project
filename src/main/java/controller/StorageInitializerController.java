package controller;

import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import relationClasses.RestaurantEmployee;
import util.SessionFactoryCfg;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class StorageInitializerController {

    List<Storage> storageInfo = new ArrayList<>();

    public StorageInitializerController(HttpSession session) {

        try (Session hibSession = new SessionFactoryCfg().getSessionFactory().openSession()) {

            //Check which restaurants the employee has access too
            Query aecQuery = hibSession.createQuery("From RestaurantEmployee where employeeId = :id");
            aecQuery.setParameter("id", session.getAttribute("employeeID"));
            RestaurantEmployee restaurantEmployee = (RestaurantEmployee) aecQuery.list().get(0);
            session.setAttribute("restaurantID", restaurantEmployee.getRestaurantId());

            //Instantiates which restaurant is chosen
            Query restaurantQuery = hibSession.createQuery("From Restaurant where id = :id");
            restaurantQuery.setParameter("id", session.getAttribute("restaurantID"));
            Restaurant restaurant = (Restaurant) restaurantQuery.uniqueResult();
            session.setAttribute("restaurant", restaurant);

            //Collect all storages belonging to the restaurant
            storageInfo = restaurant.allStorages();

        } catch (HibernateException e) {
            System.out.println("Couldn't get the list.");
            e.printStackTrace();

        }
    }

    public List<Storage> getStorageInfo() {
        return storageInfo;
    }
}
