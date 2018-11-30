package controller;

import model.SessionFactoryCfg;
import model.Transactions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HistoryController {

    private Session session;
    private List history;

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

        for(Transactions transactions : transactionsList){
            if(transactions.getProduct().toLowerCase().contains(input.toLowerCase())){
                sortedList.add(transactions);

            } else if(transactions.getName().toLowerCase().contains(input.toLowerCase())){
                sortedList.add(transactions);

            } else if(transactions.getBatch().toLowerCase().contains(input.toLowerCase())){
                sortedList.add(transactions);
            }

        }

        return sortedList;
    }
}
