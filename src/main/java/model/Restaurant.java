package model;

import controller.AssignedEmployeesController;
import controller.AssignedStorageController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Restaurant {

    /** Field */
    private int id;
    private String name;

    /** Methods */
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


    //TODO slette sessionfactory efter vi har fundet ud af det med static
    /** Create a relation between an employeeID with a restaurantID with each other */
    public void employEmployee(Employee employee) {
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();
            AssignedEmployeesController assignedEmployee = new AssignedEmployeesController();
            assignedEmployee.setRestaurantId(this.id);
            assignedEmployee.setEmployeeId(employee.getId());

            // Save the employeeID and restaurantID in database
            session.save(assignedEmployee);
            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Could not assign employee to the restaurant!");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    /** Delete the relation between an employee and restaurant */
    public void resignEmployee(Employee employee) {
        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            List<AssignedEmployeesController> employeeList = session.createQuery("FROM AssignedEmployeesController ").list();

            for (AssignedEmployeesController aec : employeeList) {

                if (aec.getEmployeeId() == employee.getId()) {
                    transaction = session.beginTransaction();
                    session.delete(aec);
                    transaction.commit();

                }

            }

        } catch (HibernateException e) {
            System.out.println("no employee to resign");
            e.printStackTrace();
        } finally {
            session.close();
        }

        // If employee is not a manager, delete entirely from database
        if(employee.getRole().equals("Medarbejder")){
            employee.removeEmployee();
        }

    }

    /** Create a relation between an existing storage and existing restaurant*/
    public void addStorage(Storage storage) {

        //TODO: Add storage in this method, instead of having it as an input parameter

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();
            AssignedStorageController assignedStorage = new AssignedStorageController();
            assignedStorage.setRestaurantId(this.id);
            assignedStorage.setStorageId(storage.getId());
            session.save(assignedStorage);
            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("No storage to assign!");
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    /** Delete relation between a storage and a restaurant */
    public void removeStorage(Storage storage) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Transaction transaction = null;

        try {
            List<AssignedStorageController> storageList = session.createQuery("FROM AssignedStorageController ").list();

            for (AssignedStorageController assignedStorage : storageList) {
                if (assignedStorage.getStorageId() == storage.getId()) {
                    transaction = session.beginTransaction();
                    session.delete(assignedStorage);
                    transaction.commit();
                }
            }

        } catch (HibernateException e) {
            System.out.println("Can't find storage to remove");
            e.printStackTrace();

        } finally {
            session.close();
        }

        //TODO: method in Storage-class, which removes the storage entirely from the database
    }
}



