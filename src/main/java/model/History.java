package model;

import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionFactoryCfg;
import util.SortHistory;

import java.util.ArrayList;
import java.util.List;

public class History {

    private List<Transactions> storageHistory;
    private List<Transactions> employeeList;
    private List<Transactions> sortedList;

    public List<Transactions> readEmployeeHistory(int id){
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Full history of a storage
        Query query = session.createQuery("FROM Transactions where employeeID=:i");
        query.setParameter("i", id);
        employeeList = query.list();

        session.close();

        employeeList.sort(new SortHistory());

        return employeeList;

    }

    /** Load history for the storage depending on input and number of entries */
    public List<Transactions> readHistory(int numberOfEntries, String input, Storage storage){
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Full history of a storage
        Query query = session.createQuery("FROM Transactions where storage_id =:i");
        query.setParameter("i", storage.getId());
        storageHistory = query.list();

        session.close();

        //Sort list newest entry to oldest
        storageHistory.sort(new SortHistory());

        //If someone has searched for something specific, return a list with only the searched item
        List<Transactions> history;
        if(!input.equals("")){
            searchHistory(input);
            //If number of entries is larger than the actual list (prevents overflow)
            if(numberOfEntries > sortedList.size()){
                numberOfEntries = sortedList.size();
            }
            history = sortedList.subList(0, numberOfEntries);
        } else {
            //Prevents overflow
            if(numberOfEntries > storageHistory.size()){
                numberOfEntries = storageHistory.size();
            }
            history = storageHistory.subList(0, numberOfEntries);
        }

        return history;
    }

    private void searchHistory(String input){
        sortedList = new ArrayList<>();

        //Can only search for employee, product and batch
        for(Transactions transactions : storageHistory){
            if(transactions.getProduct().toLowerCase().contains(input.toLowerCase())){
                sortedList.add(transactions);

            } else if(transactions.getName().toLowerCase().contains(input.toLowerCase())){
                sortedList.add(transactions);

            } else if(transactions.getBatch().toLowerCase().contains(input.toLowerCase())){
                sortedList.add(transactions);
            }

        }
    }
}
