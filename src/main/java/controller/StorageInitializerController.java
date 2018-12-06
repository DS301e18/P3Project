package controller;

import model.AssignedEmployees;
import model.AssignedStorage;
import model.SessionFactoryCfg;
import model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageInitializerController {

    List<Integer> storagesIDs = new ArrayList<>();
    List<Storage> storageInfo = new ArrayList<>();

    public StorageInitializerController(HttpSession session) {
        Session hibSession = new SessionFactoryCfg().getSessionFactory().openSession();

        try{

            //TODO: try to do, so an employee can belong to more than one restaurant
            //Check which restaurants the employee has access too
          
            Query aecList = hibSession.createQuery("From AssignedEmployees where employeeId = :i");
            aecList.setParameter("i", session.getAttribute("employeeID"));
            List<AssignedEmployees> aeclist = aecList.list();
            System.out.println(aeclist);
            System.out.println(session.getAttribute("employeeID"));

            //Er i tvivl om dette if statement er nødvendigt
            if(aeclist.get(0).getEmployeeId() == (int) session.getAttribute("employeeID")){
                session.setAttribute("restaurantID", aeclist.get(0).getRestaurantId());
            }

            //Check which storages belongs to the restaurant
            List<AssignedStorage> ascList = hibSession.createQuery("From AssignedStorage ").list();

            for(AssignedStorage asc : ascList){
                if(asc.getRestaurantId() == (int) session.getAttribute("restaurantID")){
                    storagesIDs.add(asc.getStorageId());
                }
            }

            //Get the information of all relevant storages
            List<Storage> storageList = hibSession.createQuery("From Storage ").list();

            for (Storage storage : storageList){
                for(int i = 0; i <= storagesIDs.size()-1; i++){
                    if(storage.getId() == storagesIDs.get(i)){
                        storageInfo.add(storage);
                    }
                }
            }

        } catch (HibernateException e){
            System.out.println("Couldn't get the list.");
            e.printStackTrace();

        } finally {
            hibSession.close();
        }
    }

    public List<Integer> getStoragesIDs() {
        return storagesIDs;
    }

    public void setStoragesIDs(List<Integer> storagesIDs) {
        this.storagesIDs = storagesIDs;
    }

    public List<Storage> getStorageInfo() {
        return storageInfo;
    }

    public void setStorageInfo(List<Storage> storageInfo) {
        this.storageInfo = storageInfo;
    }
}
