package model;

import Util.AddRemove;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends AddRemove {

    /**
     * Field
     */
    private int id;
    private String name;
    private SessionFactory factory;

    public Restaurant(String name) {
        this.name = name;

        addObject(this);
    }

    public Restaurant() {
    }

    /**
     * Methods
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<AssignedEmployees> collectEmployees() {
        List<AssignedEmployees> restaurantEmployees = new ArrayList<>();

        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        List<AssignedEmployees> restaurantEmployeesList = session.createQuery("FROM AssignedEmployees").list();
        for (AssignedEmployees restaurantEmployee : restaurantEmployeesList) {
            if (this.getId() == restaurantEmployee.getRestaurantId()) {
                restaurantEmployees.add(restaurantEmployee);
            }
        }

        session.close();
        return restaurantEmployees;
    }

    private List<AssignedStorage> collectStorages() {
        List<AssignedStorage> restaurantStorages = new ArrayList<>();

        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        List<AssignedStorage> restaurantStorageList = session.createQuery("FROM AssignedStorage").list();
        for (AssignedStorage restaurantStorage : restaurantStorageList) {
            if (this.getId() == restaurantStorage.getRestaurantId()) {
                restaurantStorages.add(restaurantStorage);
            }
        }

        session.close();
        return restaurantStorages;
    }

}



