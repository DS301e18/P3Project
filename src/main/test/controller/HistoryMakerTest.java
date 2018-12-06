package controller;

import model.HistoryMaker;
import model.SessionFactoryCfg;
import model.Transactions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO: Make such that data needed are created in the test itself
class HistoryMakerTest {

    private SessionFactory sessionFactory;
    private int numberOfEntries;
    private HistoryMaker history;

    @BeforeEach
    void before(){

        sessionFactory = new SessionFactoryCfg().createSessionFactory();

        numberOfEntries = 2;
        history = new HistoryMaker();

        history.readHistory(numberOfEntries);
    }

    @Test
    void readHistoryTest(){

        List historyList = history.getHistory();

        assertEquals(2, historyList.size());
    }

    @Test
    void sortHistory(){

        List<Transactions> sortedList = history.sortHistory("Kaj");

        assertEquals("Kaj", sortedList.get(0).getName());

    }

}