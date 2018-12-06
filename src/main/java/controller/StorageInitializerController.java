package controller;

import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageInitializerController {

    List<Storage> storageInfo = new ArrayList<>();

    public StorageInitializerController(HttpSession session) {
        Session hibSession = new SessionFactoryCfg().getSessionFactory().openSession();

        try{

            //TODO: try to do, so an employee can belong to more than one restaurant
            //Check which restaurants the employee has access too
          
            Query aecQuery = hibSession.createQuery("From AssignedEmployees where employeeId = :i");
            aecQuery.setParameter("i", session.getAttribute("employeeID"));
            List<AssignedEmployees> aeclist = aecQuery.list();

            //Er i tvivl om dette if statement er nødvendigt
            if(aeclist.get(0).getEmployeeId() == (int) session.getAttribute("employeeID")){
                session.setAttribute("restaurantID", aeclist.get(0).getRestaurantId());
            }

            //Instantiates which restaurant is chosen
            Query restaurantQuery = hibSession.createQuery("From Restaurant where id = :j");
            restaurantQuery.setParameter("j", session.getAttribute("restaurantID"));
            session.setAttribute("restaurant", restaurantQuery.list().get(0));
            Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
            storageInfo = restaurant.allStorages();

        } catch (HibernateException e){
            System.out.println("Couldn't get the list.");
            e.printStackTrace();

        } finally {
            hibSession.close();
        }
    }

    public List<Storage> getStorageInfo() {
        return storageInfo;
    }

    public void setStorageInfo(List<Storage> storageInfo) {
        this.storageInfo = storageInfo;
    }
}
