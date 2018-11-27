package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private SessionFactory sessionFactory;
    private Session session;

    @BeforeEach
    public void before(){
        sessionFactory = createSessionFactory();

    }


    @Test
    void employEmployeeTest(Employee employee) {

    }
}