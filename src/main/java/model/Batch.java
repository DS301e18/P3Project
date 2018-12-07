package model;

import relationClasses.ProductBatch;
import util.AddRemove;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.SessionFactoryCfg;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;

public class Batch extends AddRemove {

    /**
     * Field
     **/
    private int id;
    private String batchNumber;
    private Timestamp date;
    private int remainingInBox;
    private int originalBatchSize;
    private BigDecimal value;
    private BigDecimal originalValue;
    private String typeName;
    private Product product;

    /**
     * Methods
     **/

    //Constructor with database connectivity included
    public Batch(Product product, String batchNumber) {

        this.batchNumber = batchNumber;
        this.date = new Timestamp(System.currentTimeMillis());
        this.remainingInBox = product.getBatchSize();
        this.value = product.getPrice();
        this.typeName = product.getName();
        this.originalValue = product.getPrice();
        this.originalBatchSize = product.getBatchSize();
        this.product = product;

        addObject(this);

    }

    public Batch(Product product, String batchNumber, int numberAdded) {

        this.product = product;
        this.typeName = product.getName();
        this.batchNumber = batchNumber;
        this.date = new Timestamp(System.currentTimeMillis());

        this.remainingInBox = numberAdded;
        this.value = product.getPrice().multiply(BigDecimal.valueOf(numberAdded));

        this.originalValue = product.getPrice();
        this.originalBatchSize = numberAdded;

        addObject(this);

    }

    //Empty constructor because of AddRemove idk why
    public Batch() {
    }

    //Method that can take any amount from a batch of a product
    /** If this methond is called with the amount 0 it will remove**/
    public void takeFromBatch(ProductBatch productBatch, int amount) {

        this.setRemainingInBox(this.remainingInBox - amount);

        if(this.getRemainingInBox() < 1){
            removeObject(this);
            removeObject(productBatch);

        }else{
            calcBatchValue(remainingInBox);
        }
    }

  
    private void calcBatchValue(int amount) {

        SessionFactory sessionFactory = new SessionFactoryCfg().getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction;

        try{
            transaction = session.beginTransaction();

            MathContext mc = new MathContext(4);

            BigDecimal multiplySum = originalValue.multiply(BigDecimal.valueOf(amount), mc);

            this.setValue(multiplySum);

            session.update(this);

            transaction.commit();

        } catch (HibernateException e){
            System.out.println("Could not save the object");
            e.printStackTrace();

        } finally {
            session.close();
        }
    }


    public void setValue(BigDecimal value) {
        this.value = value;
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

    public BigDecimal getValue() {
        return value;
    }

    public int getRemainingInBox() {
        return remainingInBox;
    }

    public void setRemainingInBox(int remainingInBox) {
        this.remainingInBox = remainingInBox;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOriginalBatchSize() {
        return originalBatchSize;
    }

    public void setOriginalBatchSize(int originalBatchSize) {
        this.originalBatchSize = originalBatchSize;
    }

    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return  "batchNumber='" + batchNumber + '\'' +
                "product='" + product + '\'';
    }
}
