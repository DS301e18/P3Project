package controller;

import model.SessionFactoryCfg;
import model.Storage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class StorageInitializerController {

    List<Integer> storagesIDs = new ArrayList<>();
    List<Storage> storageInfo = new ArrayList<>();

    public StorageInitializerController(HttpSession session) {
        Session hibSession = new SessionFactoryCfg().getSessionFactory().openSession();

        try{

            //TODO: try to do, so an employee can belong to more than one restaurant
            //Check which restaurants the employee has access too

            //List<AssignedEmployeesController> aecList = hibSession.createQuery("From AssignedEmployeesController").list();
            Query aecList = hibSession.createQuery("From AssignedEmployeesController where employeeId = :i");
            aecList.setParameter("i", session.getAttribute("employeeID"));
            List<AssignedEmployeesController> aec = aecList.list();

            if(aec.get(0).getEmployeeId() == (int) session.getAttribute("employeeID")){
                session.setAttribute("restaurantID", aec.get(0).getRestaurantId());
            }

            //Check which storages belongs to the restaurant
            List<AssignedStorageController> ascList = hibSession.createQuery("From AssignedStorageController ").list();
            Query ascListt = hibSession.createQuery("From AssignedStorageController where restaurantId = :d");
            ascListt.setParameter("d", session.getAttribute("restaurantID"));
            List<AssignedStorageController> test = ascListt.list();

           // System.out.println(test);

            for(AssignedStorageController asc : ascList){
                if(asc.getRestaurantId() == (int) session.getAttribute("restaurantIDd")){
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
