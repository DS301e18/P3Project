package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductType {

    /** Field **/

    private int id;
    private String name;
    //private String productNumber;
    private int batchSize;
    private BigDecimal prize;
    private Storage storage;
    //private final List<Batch> batches = new ArrayList<Batch>();

    public ProductType(int id, String name, String productNumber, int batchSize, BigDecimal prize, Storage storage){
        this.id = id;
        this.name = name;
        //this.productNumber = productNumber;
        this.batchSize = batchSize;
        this.prize = prize;
        this.storage = storage;
    }

    public ProductType() {
    }

    /** Methods **/
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public BigDecimal getPrize() {
        return prize;
    }
    public void setPrize(BigDecimal prize){
        this.prize = prize;
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

//    public String getProductNumber() {
//        return productNumber;
//    }
//
//    public void setProductNumber(String productNumber) {
//        this.productNumber = productNumber;
//    }

    public int getBatchSize() {
        return batchSize;
    }
    //funktion til at tælle antal af vare op. negativt argument fjerner antal.
    public void setBatchSize(int factor) {
        this.batchSize += factor;
    }


}

