package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductType {

    /** Field **/
    private Storage storage;
    private int id;
    private String name;
    private String productNumber;   // Purpose?
    private int batchSize;  // Redundant
    private double batchCost;   // Redundant
    private BigDecimal cost;
    private final List<Batch> batches = new ArrayList<Batch>();

    // Constructor
    public ProductType(String name, String productNumber, int id, BigDecimal prize){
        this.id = id;
        this.name = name;
        this.productNumber = productNumber;
        this.cost = prize;
    }

    /** Methods **/
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public BigDecimal getCost() {
        return cost;
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

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public int getBatchSize() {
        return batchSize;
    }
    //funktion til at tælle antal af vare op. negativt argument fjerner antal.
    public void setBatchSize(int factor) {
        this.batchSize += factor;
    }

    // Use cost field.
    public double getBatchCost() {
        return batchCost;
        // return this.cost;
    }

    // Might be redundant or change method name to 'setCost'.
    public void setBatchCost(int batchCost) {
        this.batchCost = batchCost;
    }

}
