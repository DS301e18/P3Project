package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.List;

public class Product {

    /**
     * Field
     **/

    private int id;
    private String name;
    private int batchSize;
    private BigDecimal price;
    private Storage storage;
    private int storageID;
    //private final List<Batch> batches = new ArrayList<Batch>();

    public Product(String name, int batchSize, BigDecimal price, int storageID) {
        this.name = name;
        //this.productNumber = productNumber;
        this.batchSize = batchSize;
        this.price = price;
        this.storageID = storageID;
    }

    public Product() {
    }

    /**
     * Methods
     **/
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

    public int getBatchSize() {
        return batchSize;
    }

    //funktion til at tælle antal af vare op. negativt argument fjerner antal.
    public void setBatchSize(int factor) {
        this.batchSize += factor;
    }

    public int getStorageID() {
        return storageID;
    }

    public void setStorageID(int storageID) {
        this.storageID = storageID;
    }

    //TODO Gør sådan at den tjekker hvilket lager en bestemt varetype/batch ligger. Ellers kommer denne kode til at tage alle batches pris i alle lagre.
    public BigDecimal priceOfAllBatches() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        BigDecimal totalPrice = new BigDecimal(0);

        try {
            List<Batch> batchList = session.createQuery("FROM Batch").list();
            for (Batch batch : batchList) {
                totalPrice = totalPrice.add(batch.getValue());
            }

        } catch (HibernateException e) {
            System.out.println("Couldn't find any products");
            e.printStackTrace();
        } finally {
            session.close();
        }

        return totalPrice;
    }

}

