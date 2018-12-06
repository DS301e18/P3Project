package model;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HistoryMaker {

    private Session session;
    private List<Transactions> history;

    public List getHistory() {
        return history;
    }

    public void readHistory(int numberOfEntries){

        session = new SessionFactoryCfg().getSessionFactory().openSession();

        Query query = session.createQuery("FROM Transactions");
        query.setMaxResults(numberOfEntries);
        history = query.list();

        session.close();
    }

    public List<Transactions> sortHistory(String input){
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
