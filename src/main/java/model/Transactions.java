package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.text.SimpleDateFormat;

import org.hibernate.Transaction;
import util.AddRemove;
import util.SessionFactoryCfg;


import java.sql.Timestamp;

public class Transactions extends AddRemove {

    /**
     * Field
     **/
    private int id;
    private String name;
    private int employeeID;
    private String batch;
    private String product;
    private String timestamp;
    private int amount;
    private String transtype;
    private Storage storage;
    private int storage_id;
    private String storageName;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");


    /**
     * Methods
     **/

    public void registerTransaction(Storage storage, Employee employee, Batch batch, int amount, String transtype) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        this.storage_id = storage.getId();
        this.employeeID = employee.getId();
        this.timestamp = sdf.format(timestamp);
        this.name = employee.getFirstName() + " " + employee.getLastName();
        this.batch = batch.getBatchNumber();
        this.product = batch.getTypeName();
        this.amount = amount;
        this.transtype = transtype;
        this.storageName = storage.getName();

        addObject(this);
    }

    //Getters and setters
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getStorage_id() {
        return storage_id;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public void setStorage_id(int storage_id) {
        this.storage_id = storage_id;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transactions that = (Transactions) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (storage_id != that.storage_id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (batch != null ? !batch.equals(that.batch) : that.batch != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return transtype != null ? transtype.equals(that.transtype) : that.transtype == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + batch.hashCode();
        result = 31 * result + product.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + amount;
        result = 31 * result + transtype.hashCode();
        result = 31 * result + storage_id;
        return result;
    }
}
