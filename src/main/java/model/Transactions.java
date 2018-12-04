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
    private Timestamp timestamp;
    private int amount;
    private String transtype;

    /** Methods **/

    public void registerTransaction(Employee employee, Batch batch, int amount, String transtype) {

        this.timestamp = getTimestamp();
        this.name = employee.getFirstName();
        this.batch = batch.getBatchNumber();
        this.product = batch.getProduct().getName();
        this.amount = amount;
        this.transtype = transtype;

        //TODO: Delete sessionFactory later
        SessionFactory sessionFactory = new SessionFactoryCfg().createSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction;

        try {
            transaction = session.beginTransaction();
            session.save(this);
            transaction.commit();

        } catch (HibernateException e){
            System.out.println("Could not save the transaction");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {return timestamp;}

    public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transactions that = (Transactions) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (!name.equals(that.name)) return false;
        if (!batch.equals(that.batch)) return false;
        if (!product.equals(that.product)) return false;
        return transtype.equals(that.transtype);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (batch != null ? batch.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (transtype != null ? transtype.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return '\n'+"Transactions:" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", batch='" + batch + '\'' +
                ", product='" + product + '\'' +
                ", date=" + timestamp +
                ", amount=" + amount +
                ", transtype='" + transtype;
    }
}
