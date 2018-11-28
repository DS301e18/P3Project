package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class Storage {

    /**
     * Field
     */
    private int id;
    private String name;
    private Restaurant restaurant;

    public Storage(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }

    /**
     * Methods
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addProduct(String name, int batchSize, BigDecimal price) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            ProductType productType = new ProductType(name, batchSize, price, getId());
            session.save(productType);
            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Couldn't save");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void removeProduct(ProductType productType) {
    }


    public void collectProducts() {

    }

    public void sortProducts() {

    }

    public void calculateTotalPrice() {

    }

}
