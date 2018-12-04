package model;

import java.math.BigDecimal;

public class Product {

    /** Field **/

    private int id;
    private String name;
    private int batchSize;
    private BigDecimal price;
    //private final List<Batch> batches = new ArrayList<Batch>();

    public Product(String name, int batchSize, BigDecimal price){
        this.name = name;
        //this.productNumber = productNumber;
        this.batchSize = batchSize;
        this.price = price;

    }

    public Product() {
    }

    /** Methods **/


    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price){
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
}

