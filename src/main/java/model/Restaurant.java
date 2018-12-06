package model;

import Util.AddRemove;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Comparator;
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

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

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

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<AssignedStorage> restaurantStorageList = session.createQuery("FROM AssignedStorage").list();
        for (AssignedStorage restaurantStorage : restaurantStorageList) {
            if (this.getId() == restaurantStorage.getRestaurantId()) {
                restaurantStorages.add(restaurantStorage);
            }
        }

        session.close();
        return restaurantStorages;
    }

    public List<Employee> sortEmployees() {
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<AssignedEmployees> restaurantEmployee = collectEmployees();
        List<Employee> employeeList = session.createQuery("FROM Employee").list();
        List<Employee> allRestaurantEmployees = new ArrayList<>();

        for (int i = 0; i < restaurantEmployee.size(); i++) {
            for (Employee employee : employeeList) {
                if (employee.getId() == restaurantEmployee.get(i).getEmployeeId()) {
                    allRestaurantEmployees.add(employee);
                }
            }
        }
        allRestaurantEmployees.sort(Comparator.comparing(Employee::getFirstName));
        return allRestaurantEmployees;
    }

    public List<Storage> allStorages() {
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<AssignedStorage> restaurantStorage = collectStorages();
        List<Storage> storageList = session.createQuery("FROM Storage").list();
        List<Storage> allRestaurantStorages = new ArrayList<>();

        for (int i = 0; i < restaurantStorage.size(); i++) {
            for (Storage storage : storageList) {
                if (storage.getId() == restaurantStorage.get(i).getStorageId()) {
                    allRestaurantStorages.add(storage);
                }
            }
        }
        return allRestaurantStorages;
    }

    @Override
    public String toString() {
        return  name;
    }
}



