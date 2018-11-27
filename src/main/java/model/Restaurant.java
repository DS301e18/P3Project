package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Restaurant {

    /** Field **/
    private int id;
    private String name;
    private SessionFactory factory;

    /** Methods **/
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*private void employEmployee(){
        factory = new SessionFactoryCfg().createSessionFactory();
        Session session = factory.openSession();

        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            Employee employee = new Employee();

        }



    }*/


}
