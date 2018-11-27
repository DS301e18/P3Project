package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;

public class Batch {

    /**
     * Field
     **/
    private int id;
    private String batchNumber;
    private Timestamp date;
    private int productAmount;
    private BigDecimal price;
    private ProductType productType;
    //private List<ProductType> batchList = new ArrayList<ProductType>();

    /**
     * Methods
     **/

    //Constructor
    public Batch(ProductType productType) {

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            this.id = productType.getId();
            this.batchNumber = productType.getProductNumber();
            this.date = new Timestamp(System.currentTimeMillis());
            this.productAmount = productType.getBatchSize();
            this.price = calcBatchPrice(productType);

            session.save(this);
            transaction.commit();
        }
        catch (HibernateException e){
            System.out.println("Could not save the transaction");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void takeFromBatch(Batch batch, int amount) {
        batch.productAmount = batch.productAmount - amount;
    }


    private BigDecimal calcBatchPrice(ProductType productType) {

        MathContext mc = new MathContext(2);

        BigDecimal a = BigDecimal.valueOf(productType.getBatchSize());
        BigDecimal b = productType.getCost();

        return a.multiply(b, mc);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
