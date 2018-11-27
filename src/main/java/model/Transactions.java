package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.sql.Timestamp;

public class Transactions {

    /** Field **/
    private int id;
    private String name;
    private String batch;
    private String product;
    private Timestamp date;
    private int amount;
    private String transtype;
    private Session session;
    private SessionFactory sessionFactory;

    /** Methods **/

    public void registerTransaction(Employee employee, Batch batch, ProductType product, int amount, String transtype) {

        //TODO: Delete sessionFactory later
        sessionFactory = new SessionFactoryCfg().createSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            this.date = getDate();
            this.name = employee.getFirstname();
            this.batch = batch.getBatchNumber();
            this.product = product.getProductNumber();
            this.amount = amount;
            this.transtype = transtype;

            session.save(this);
            transaction.commit();

        } catch (HibernateException e){
            System.out.println("Could not save the transaction");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }
}
