package model;

import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionFactoryCfg;
import util.SortHistory;

import java.util.ArrayList;
import java.util.List;

public class HistoryMaker {

    private Session session;
    private List<Transactions> history;

    public List getHistory() {
        return history;
    }

    public void readHistory(int numberOfEntries, Storage storage){

        session = new SessionFactoryCfg().getSessionFactory().openSession();

        Query query = session.createQuery("FROM Transactions where storage_id = :i");
        query.setParameter("i", storage.getId());
        List<Transactions> storageHistory = query.list();

        session.close();

        storageHistory.sort(new SortHistory());

        if(numberOfEntries > storageHistory.size()){
            numberOfEntries = storageHistory.size();
        }

        history = storageHistory.subList(0, numberOfEntries);
    }

    public List<Transactions> searchHistory(String input){
        session = new SessionFactoryCfg().getSessionFactory().openSession();

        List<Transactions> transactionsList = getHistory();
        List<Transactions> sortedList = new ArrayList<>();

        //TODO: find a method without this many if statements
        for(Transactions transactions : transactionsList){
            if(transactions.getProduct().toLowerCase().contains(input.toLowerCase())){
                sortedList.add(transactions);

            } else if(transactions.getName().toLowerCase().contains(input.toLowerCase())){
                sortedList.add(transactions);

            } else if(transactions.getBatch().toLowerCase().contains(input.toLowerCase())){
                sortedList.add(transactions);
            }

        }

        session.close();

        return sortedList;
    }
}
