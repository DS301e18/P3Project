package model;

import controller.AssignedEmployeesController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class Restaurant {

    /**
     * Field
     */
    private int id;
    private String name;
    private SessionFactory factory;

    /**
     * Methods
     */
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
    public void employEmployee(Employee employee) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();
            AssignedEmployeesController assignedEmployee = new AssignedEmployeesController();
            assignedEmployee.setRestaurantId(this.id);
            assignedEmployee.setEmployeeId(employee.getId());
            session.save(assignedEmployee);
            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Nothing to assign");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void resignEmployee(Employee employee) {
        Session session = factory.openSession();

        Transaction transaction = null;

        try {
            List<AssignedEmployeesController> employeeList = session.createQuery("FROM AssignedEmployeesController ").list();
            for (AssignedEmployeesController aec : employeeList) {
                if (aec.getEmployeeId() == employee.getId()) {
                    transaction = session.beginTransaction();
                    session.delete(aec);
                    transaction.commit();
                }
            }

        } catch (HibernateException e) {
            System.out.println("nothing to resign");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}