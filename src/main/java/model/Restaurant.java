package model;

import util.AddRemove;

import org.hibernate.Session;
import relationClasses.RestaurantEmployee;
import relationClasses.RestaurantStorage;
import util.SessionFactoryCfg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Restaurant extends AddRemove {

    /**
     * Field
     */
    private int id;
    private String name;

    /**
     * Methods
     */

    //Constructor with database connectivity included
    public Restaurant(String name) {
        this.name = name;

        addObject(this);
    }

    //Empty constructor because of AddRemove.
    public Restaurant() {
    }

    //Getter and setter
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

    //Returns a list of all employees in a restaurant
    public List<RestaurantEmployee> collectEmployees() {
        //New arraylist of RestaurantEmployee
        List<RestaurantEmployee> restaurantEmployees = new ArrayList<>();

        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list with the relation data in the database
        List<RestaurantEmployee> restaurantEmployeesList = session.createQuery("FROM RestaurantEmployee ").list();

        //Check if the current restaurant id matches one from the list
        for (RestaurantEmployee restaurantEmployee : restaurantEmployeesList) {
            if (this.getId() == restaurantEmployee.getRestaurantId()) {
                restaurantEmployees.add(restaurantEmployee);
            }
        }

        session.close();

        //returns the list of relations between the current restaurant and employees
        return restaurantEmployees;
    }

    //Returns a list of all restaurantStorages where restaurant is this restaurant
    public List<RestaurantStorage> collectStorages() {
        //New arraylist of restaurantStorages
        List<RestaurantStorage> restaurantStorages = new ArrayList<>();

        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list with the relation data in the database
        List<RestaurantStorage> restaurantStorageList = session.createQuery("FROM RestaurantStorage ").list();

        //Check if the current restaurant id matches one from the list
        for (RestaurantStorage restaurantStorage : restaurantStorageList) {
            if (this.getId() == restaurantStorage.getRestaurantId()) {
                restaurantStorages.add(restaurantStorage);
            }
        }

        session.close();

        //returns the list of relations between the current restaurant and employees
        return restaurantStorages;
    }

    //Returns a sorted list of employees by name
    public List<Employee> sortEmployees() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list of RestaurantEmployee with the data from the method call: collectEmployees
        List<RestaurantEmployee> restaurantEmployee = collectEmployees();

        //Makes a list with the employee data in the database
        List<Employee> employeeList = session.createQuery("FROM Employee").list();

        //New arraylist of Employee
        List<Employee> allRestaurantEmployees = new ArrayList<>();

        //Compares the arraylist of relations between the employee array list. Add to list if their id matches and the role is also "medarbejder"
        for (int i = 0; i < restaurantEmployee.size(); i++) {
            for (Employee employee : employeeList) {
                if (employee.getId() == restaurantEmployee.get(i).getEmployeeId() && employee.getRole().equals("Medarbejder")) {
                    //Add employee to allRestaurantEmployees
                    allRestaurantEmployees.add(employee);
                }
            }
        }

        //Sort allRestaurantEmployees list with a Comparator comparing Employee with their first name
        allRestaurantEmployees.sort(Comparator.comparing(Employee::getFirstName));

        session.close();
        //Returning a sorted arraylist of employees of this restaurant
        return allRestaurantEmployees;
    }

    //Returns a list of all storages in a restaurant
    public List<Storage> allStorages() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list of RestaurantStorage with the data from the method call: collectStorages
        List<RestaurantStorage> restaurantStorage = collectStorages();

        //Makes a list with the storage data in the database
        List<Storage> storageList = session.createQuery("FROM Storage").list();

        //New arraylist of storages
        List<Storage> allRestaurantStorages = new ArrayList<>();

        //Compares the arraylist of relations between the storage array list. Add to list if their id matches
        for (int i = 0; i < restaurantStorage.size(); i++) {
            for (Storage storage : storageList) {
                if (storage.getId() == restaurantStorage.get(i).getStorageId()) {
                    allRestaurantStorages.add(storage);
                }
            }
        }

        session.close();
        //Returning an arraylist of all storages of this restaurant
        return allRestaurantStorages;
    }

    //Removes a restaurant and all relations with it in the database
    public void removeRestaurant() {
        //If allStorages list is not null
        if (allStorages() != null) {
            //For the size of allStorages
            for (int i = 0; i < allStorages().size(); i++) {
                //remove all indexes of storages with the method call found in the Storage class
                allStorages().get(i).remove();
            }
        }

        //If collectStorages list is not null
        if (collectStorages() != null) {
            //For the size of collectStorages
            for (int i = 0; i < collectStorages().size(); i++) {
                //remove all indexes of restaurantStorage relations with the method call found in the restaurantStorage class
                collectStorages().get(i).remove();
            }
        }

        //If sortEmployees list is not null
        if (sortEmployees() != null) {
            //For the size of sortEmployees
            for (int i = 0; i < sortEmployees().size(); i++) {
                //remove all indexes of employee objects created with the employee constructor.
                removeObject(sortEmployees().get(i));
            }
        }

        //If collectEmployees list is not null
        if (collectEmployees() != null) {
            //For the size of collectEmployees
            for (int i = 0; i < collectEmployees().size(); i++) {
                //remove all indexes of restaurantEmployee objects created with the restaurantEmployee constructor.
                removeObject(collectEmployees().get(i));
            }
        }

        //Removes this restaurant object)
        removeObject(this);
    }

    //Update
    public void update() {
        updateObject(this);
    }
}



