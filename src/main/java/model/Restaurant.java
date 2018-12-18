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

        //Opens a session from sessionFactoryCfg
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list with the relation data in the database
        List<RestaurantEmployee> restaurantEmployeesList = session.createQuery("FROM RestaurantEmployee ").list();
        //For each restaurantEmployee in the above list
        for (RestaurantEmployee restaurantEmployee : restaurantEmployeesList) {
            //If this restaurant ID is the same as the restaurantEmployee.getRestaurantId()
            if (this.getId() == restaurantEmployee.getRestaurantId()) {
                //Adds this restaurantEmployee to the restaurantEmployees arraylist
                restaurantEmployees.add(restaurantEmployee);
            }
        }
        //Closes the session
        session.close();
        //returns the arraylist
        return restaurantEmployees;
    }

    //Returns a list of all restaurantStorages where restaurant is this restaurant
    public List<RestaurantStorage> collectStorages() {
        //New arraylist of restaurantStorages
        List<RestaurantStorage> restaurantStorages = new ArrayList<>();

        //Opens a session from sessionFactoryCfg
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list with the relation data in the database
        List<RestaurantStorage> restaurantStorageList = session.createQuery("FROM RestaurantStorage ").list();
        //For each restaurantStorage in the above list
        for (RestaurantStorage restaurantStorage : restaurantStorageList) {
            //If this storage ID is the same as the restaurantStorage.getRestaurantId()
            if (this.getId() == restaurantStorage.getRestaurantId()) {
                //Adds this restaurantStorage to the restaurantStorages arraylist
                restaurantStorages.add(restaurantStorage);
            }
        }
        //Closes the session
        session.close();
        //returns the arraylist
        return restaurantStorages;
    }

    //Returns a sorted list of employees by name
    public List<Employee> sortEmployees() {
        //Opens a session from sessionFactoryCfg
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list of RestaurantEmployee with the data from the method call: collectEmployees
        List<RestaurantEmployee> restaurantEmployee = collectEmployees();

        //Makes a list with the employee data in the database
        List<Employee> employeeList = session.createQuery("FROM Employee").list();

        //New arraylist of Employee
        List<Employee> allRestaurantEmployees = new ArrayList<>();

        //Start i in 0, until i < size of restaurantEmployee, increase with 1 each loop
        for (int i = 0; i < restaurantEmployee.size(); i++) {
            //For each employee in employeeList
            for (Employee employee : employeeList) {
                //If employee.getId is the same as the employeeId found in the relation list and employee.getRole is "medarbejder"
                if (employee.getId() == restaurantEmployee.get(i).getEmployeeId() && employee.getRole().equals("Medarbejder")) {
                    //Add employee to allRestaurantEmployees
                    allRestaurantEmployees.add(employee);
                }
            }
        }
        //Sort allRestaurantEmployees list with a Comparator comparing Employee with their first name
        allRestaurantEmployees.sort(Comparator.comparing(Employee::getFirstName));
        //Returning a sorted arraylist of employees of this restaurant
        return allRestaurantEmployees;
    }

    //Returns a list of all storages in a restaurant
    public List<Storage> allStorages() {
        //Opens a session from sessionFactoryCfg
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list of RestaurantStorage with the data from the method call: collectStorages
        List<RestaurantStorage> restaurantStorage = collectStorages();
        //Makes a list with the storage data in the database
        List<Storage> storageList = session.createQuery("FROM Storage").list();
        //New arraylist of storages
        List<Storage> allRestaurantStorages = new ArrayList<>();

        //Start i in 0, until i < size of restaurantStorage, increase with 1 each loop
        for (int i = 0; i < restaurantStorage.size(); i++) {
            //For each storage in storageList
            for (Storage storage : storageList) {
                //If storage.getId is the same as the storage found in the relation list
                if (storage.getId() == restaurantStorage.get(i).getStorageId()) {
                    //Add storage to allRestaurantStorages
                    allRestaurantStorages.add(storage);
                }
            }
        }
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



