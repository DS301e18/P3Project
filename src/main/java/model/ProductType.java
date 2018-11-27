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

    public ProductType() {
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

    // TODO: Purpose?
    // GUESS: Used to count amount of a certain product type from all batches.
    public int gatherBatches(){
        int counter = 0;

        for (Batch batch : this.batches){
            counter += batch.getProductAmount();
        }

        return counter;
    }

    // Exchanges two elements in list.
    private void exchange(Batch batch1, Batch batch2)
    {
        Batch exchanger = batch1;
        int index1 = this.batches.indexOf(batch1), index2 = this.batches.indexOf(batch2);
        this.batches.remove(index1);
        this.batches.remove(index2);
        this.batches.add(index1, batch2);
        this.batches.add(index2, batch1);
    }

    // Sorts by date.
    public void sortBatches(){
        int size = this.batches.size();

        for (int i = 0; i < size; i++){
            for (int j = 1; j < size; j++){

                if (this.batches.get(i).getDate().getTime() < this.batches.get(i - 1).getDate().getTime())
                {
                    exchange(this.batches.get(i), this.batches.get(i - 1));
                }
            }
        }
    }

    public double beregnPris(){
        return this.batchSize * this.batchCost;


    }

    public void addBatches(Batch batch){
        this.batches.add(batch);

    }
/**
    public static void main(String[] args) {

        ProductType cola = new ProductType("coca cola", "22", 89, 109);
        ProductType faxwKondi = new ProductType("Faxe Kondi", "15", 75,89);

        //cola.addBatches(cola);


    }
**/
}
