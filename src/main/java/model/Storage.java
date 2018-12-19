package model;

import util.AddRemove;
import org.hibernate.Session;
import relationClasses.StorageProduct;
import util.SessionFactoryCfg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Storage extends AddRemove {

    /**
     * Field
     */
    private int id;
    private String name;

    /**
     * "Methods
     */

    //Empty constructor because of AddRemove.
    public Storage() {
    }

    //Constructor with database connectivity included
    public Storage(String name) {
        this.name = name;

        addObject(this);
    }

    //Remove method
    public void remove() {
        if ((sortProducts() != null)) {
            for (int i = 0; i < sortProducts().size(); i++) {
                sortProducts().get(i).remove();
            }
        }

        if ((collectProducts() != null)) {
            for (int i = 0; i < collectProducts().size(); i++) {
                collectProducts().get(i).remove();
            }
        }

        removeObject(this);

    }

    //Update method
    public void update() {
        updateObject(this);
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

    /**
     * Returns a list of all productIds in this storage.
     * Creates a list of StorageProductsControllers to get all ids of the products in this storage.
     * The for each loop is looking for this storageId with all storageIds in the StorageProduct to ensure its this storage.
     * If the ids are the same the productId is added to the list.
     */
    private List<StorageProduct> collectProducts() {
        //New arraylist of StorageProduct
        List<StorageProduct> storageProducts = new ArrayList<>();

        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list with the relation data in the database
        List<StorageProduct> storageProductList = session.createQuery("FROM StorageProduct").list();

        //Check if the current storage id matches one from the list
        for (StorageProduct storageProduct : storageProductList) {
            if (this.getId() == storageProduct.getStorageId()) {
                storageProducts.add(storageProduct);
            }
        }

        session.close();

        //returns the list of relations between the current storage and products
        return storageProducts;
    }

    /**
     * Returns a sorted by name list of all products in this storage. This list is called totalStorageProducts.
     * A method call to collectProducts is made to get a list of all productIds in this storage.
     * Creates a productList with all products in the database.
     * Creates a totalStorageProduct list.
     * The for loop and for each loop is made to compare all storageProductIds with all products in the database to add them
     * in a the returned list called totalStorageProducts.
     */
    public List<Product> sortProducts() {
        Session session = SessionFactoryCfg.getSessionFactory().openSession();

        //Makes a list of StorageProduct with the data from the method call: collectProducts
        List<StorageProduct> storageProducts = collectProducts();
        //Makes a list with the Product data in the database
        List<Product> productList = session.createQuery("FROM Product").list();

        //New arraylist of Product
        List<Product> totalStorageProducts = new ArrayList<>();

        //Compares the arraylist of relations between the product array list. Add to list if their id matches
        for (int i = 0; i < storageProducts.size(); i++) {
            for (Product product : productList) {
                if (product.getId() == storageProducts.get(i).getProductId()) {
                    totalStorageProducts.add(product);
                }
            }
        }
        //Sort totalStorageProducts list with a Comparator comparing Product with their name
        totalStorageProducts.sort(Comparator.comparing(Product::getName));

        session.close();
        //Returning a sorted arraylist of sorted products in this storage
        return totalStorageProducts;
    }

    /**
     * Returns a totalPrice of all products in a storage.
     * Creates a list of all products in this storage with a method call to sortProducts.
     * Ensures that totalPrice start at 0.
     * The for loop goes through all products in this storage to get their prices and stores it in totalPrice.
     * To ensure that we get the price of all batches of all products a method call of priceOfAllBatches in the product class is made.
     */
    public BigDecimal calculateTotalPrice() {
        List<Product> storageProducts = sortProducts();
        BigDecimal totalPrice = new BigDecimal(0);

        for (Product storageProduct : storageProducts) {
            totalPrice = totalPrice.add(storageProduct.priceOfAllBatches());
        }
        return totalPrice;
    }
}
