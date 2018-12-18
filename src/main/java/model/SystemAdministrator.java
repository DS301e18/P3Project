package model;

import org.hibernate.*;
import util.AddRemove;
import util.SessionFactoryCfg;

import java.util.List;

public class SystemAdministrator extends AddRemove {
    /**
     * Methods
     **/

    //Returns a list of all restaurants in the database
    public static List<Restaurant> collectRestaurants() {
        //Opens a session from sessionFactoryCfg
        Session session = SessionFactoryCfg.getSessionFactory().openSession();
        //Makes a list with the restaurant data in the database
        List<Restaurant> restaurantList = session.createQuery("FROM Restaurant").list();
        //Closes the session
        session.close();
        //Returns an arraylist of all restaurant
        return restaurantList;
    }

    //Returns a list of all managers in the database
    public static List<Manager> collectManagers() {
        //Empty list of managers
        List<Manager> managerList;

        //Opens a session from sessionFactoryCfg
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Query for all employees with the role as "chef"
        Query employeeQuery = session.createQuery("FROM Employee where role=:i");
        employeeQuery.setParameter("i", "Chef");

        //Assigns managerList to the query list made just above
        managerList = employeeQuery.list();

        //Closes the session
        session.close();

        //Returns a list of all managers
        return managerList;
    }
}
