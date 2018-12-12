package model;

import org.hibernate.*;
import util.AddRemove;
import util.SessionFactoryCfg;

import java.util.ArrayList;
import java.util.List;

public class SystemAdministrator extends AddRemove {
    /**
     * Methods
     **/
    public static List<Restaurant> collectRestaurants() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();
        List<Restaurant> restaurantList = session.createQuery("FROM Restaurant").list();
        session.close();
        return restaurantList;
    }

    public static List<Manager> collectManagers() {
        List<Manager> managerList;

        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        Query employeeQuery = session.createQuery("FROM Employee where role=:i");
        employeeQuery.setParameter("i", "Chef");

        managerList = employeeQuery.list();

        session.close();

        return managerList;
    }
}
