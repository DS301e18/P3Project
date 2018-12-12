package controller;

import model.History;
import util.SessionFactoryCfg;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO: Make such that data needed are created in the test itself
class HistoryTest {

    private SessionFactory sessionFactory;
    private int numberOfEntries;
    private History history;

    @BeforeEach
    void before(){

        sessionFactory = SessionFactoryCfg.getSessionFactory();

        numberOfEntries = 2;
        history = new History();

        //TODO: Make test again
        //history.readHistory(numberOfEntries);
    }

    @Test
    void readHistoryTest(){

        //List historyList = history.getHistory();

        //assertEquals(2, historyList.size());
    }

    @Test
    void sortHistory(){

        //List<Transactions> sortedList = history.searchHistory("Kaj");

        //assertEquals("Kaj", sortedList.get(0).getName());

    }

}