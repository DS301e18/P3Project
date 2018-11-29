package model;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    private SessionFactory sessionFactory;
    private Session session;

    @BeforeEach
    public void before(){
        sessionFactory = createSessionFactory();
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }

    @Test
    void employEmployeeTest(Employee employee) {
    }




}