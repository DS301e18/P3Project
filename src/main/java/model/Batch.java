package model;

import Util.AddRemove;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;

public class Batch extends AddRemove {

    /**
     * Field
     **/
    int id;
    private String batchNumber;
    private Timestamp date;
    private int remainingInBox;
    private BigDecimal value;
    private Product product;
    private String typeName;

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
            this.product = product;

            addObject(this);

    }

    //Empty constructor because of AddRemove idk why
    public Batch(){}

    //Method that can take any amount from a batch of a product
    /** If this methond is called with the amount 0 it will remove**/
    public void takeFromBatch(Batch batch,ProductBatch productBatch, int amount) {
        if (amount < 0){System.out.println("");}
        if (amount == 0){
            batch.setRemainingInBox(batch.remainingInBox - 1);
        }
        else{
            batch.setRemainingInBox(batch.remainingInBox - amount);
        }
        calcBatchValue(batch, amount);
        removeIfZero(batch, productBatch);
    }

    private void calcBatchValue(Batch batch, int amount){
        if (amount == 0){amount = 1;}

        MathContext mc = new MathContext(2);

        BigDecimal batchsize = BigDecimal.valueOf(batch.product.getBatchSize());
        BigDecimal bAmount = BigDecimal.valueOf(amount);

        BigDecimal oneFracion = batch.product.getPrice().divide(batchsize, mc);
        BigDecimal multiplySum = oneFracion.multiply(bAmount, mc);


        batch.setValue(multiplySum);
    }

    private void removeIfZero (Batch batch, ProductBatch productBatch){
        if(batch.getRemainingInBox() < 1){
            removeObject(batch);
            removeObject(productBatch);
        }
    }


    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
