package model;

import controller.AssignedEmployeesController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Restaurant {

    /** Field */
    private int id;
    private String name;
    private SessionFactory factory;

    /** Methods */
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


    //TODO slette sessionfactory efter vi har fundet ud af det med static
    private void employEmployee(Employee employee){
        factory = new SessionFactoryCfg().createSessionFactory();
        Session session = factory.openSession();

        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            AssignedEmployeesController assignedEmployee = new AssignedEmployeesController();
            assignedEmployee.setRestaurantId(this.id);
            session.save(assignedEmployee);
            transaction.commit();

        } catch (HibernateException e){
            System.out.println("Nothing to assign");
            e.printStackTrace();
            factory.close();
        } finally {
            session.close();
        }
    }
}
