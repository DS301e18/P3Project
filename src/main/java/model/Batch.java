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
    int id;
    private String batchNumber;
    private Timestamp date;
    private int remainingInBox;
    private BigDecimal value;
    private ProductType productType;
    private String typeName;
    //private List<ProductType> batchList = new ArrayList<ProductType>();

    /**
     * Methods
     **/

    //Constructor
    public Batch(ProductType productType, String batchNumber) {

        Session session = new SessionFactoryCfg().getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            this.batchNumber = batchNumber;
            this.date = new Timestamp(System.currentTimeMillis());
            this.remainingInBox = productType.getBatchSize();
            this.value = productType.getCost();
            this.typeName = productType.getName();
            this.productType = productType;

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

    public Batch(){}


    public void takeFromBatch(Batch batch, int amount) {
        if (amount == 0){
            batch.setRemainingInBox(batch.remainingInBox - 1);
        }
        else{
            batch.setRemainingInBox(batch.remainingInBox - amount);
        }
        calcBatchValue(batch, amount);
    }

    private void calcBatchValue(Batch batch, int amount){
        if (amount == 0){amount = 1;}

        MathContext mc = new MathContext(2);

        BigDecimal batchsize = BigDecimal.valueOf(batch.productType.getBatchSize());
        BigDecimal bAmount = BigDecimal.valueOf(amount);

        BigDecimal oneFracion = batch.productType.getCost().divide(batchsize, mc);
        BigDecimal multiplySum = oneFracion.multiply(bAmount, mc);


        batch.setValue(multiplySum);
    }


    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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
