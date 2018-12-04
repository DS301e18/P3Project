package model;

import Util.AddRemove;
import controller.StorageProductController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Storage extends AddRemove {

    /** Field */
    private int id;
    private String name;

    public Storage() {
    }

    public Storage(String name) {
        this.name = name;

        addObject(this);
    }

    /** Methods */
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
     * Relates a product to this storage and adds it to the database.
     */
    //TODO change createSessionFactory to getSessionFactory
    public void relateProductToStorage(Product product) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            StorageProductController storageProduct = new StorageProductController();
            storageProduct.setStorageId(this.id);
            storageProduct.setProductId(product.getId());

            session.save(storageProduct);
            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Couldn't create a relation");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Unrelates a product to this storage and removes it from the database.
     */
    //TODO change createSessionFactory to getSessionFactory
    public void unrelateProductFromStorage(Product product) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        Transaction transaction = null;

        try {
            List<StorageProductController> storageProductList = session.createQuery("FROM StorageProductController").list();
            for (StorageProductController storageProducts : storageProductList) {
                if (storageProducts.getProductId() == product.getId()) {
                    transaction = session.beginTransaction();
                    session.delete(storageProducts);
                    transaction.commit();
                }
            }
        } catch (HibernateException e) {
            System.out.println("Couldn't unrelate any products");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Creates a Product and adds it to the database.
     */
    //TODO change createSessionFactory to getSessionFactory
    public void createProduct(String name, int batchSize, BigDecimal price) {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        Transaction transaction;

        try {
            transaction = session.beginTransaction();

            Product product = new Product(name, batchSize, price);
            session.save(product);

            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Couldn't save");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Returns a list of all productIds in this storage.
     * Creates a list of StorageProductsControllers to get all ids of the products in this storage.
     * The for each loop is looking for this storageId with all storageIds in the StorageProductController to ensure its this storage.
     * If the ids are the same the productId is added to the list.
     */
    //TODO change createSessionFactory to getSessionFactory
    private List<StorageProductController> collectProducts() {
        List<StorageProductController> storageProducts = new ArrayList<>();

        Session session = new SessionFactoryCfg().createSessionFactory().openSession();
        try {
            List<StorageProductController> storageProductList = session.createQuery("FROM StorageProductController ").list();
            for (StorageProductController storageProduct : storageProductList) {
                if (this.getId() == storageProduct.getStorageId()) {
                    storageProducts.add(storageProduct);
                }
            }
        } catch (HibernateException e) {
            System.out.println("Couldn't find any products in this storage");
            e.printStackTrace();
        } finally {
            session.close();
            return storageProducts;
        }
    }

    /**
     * Returns a sorted by name list of all products in this storage. This list is called totalStorageProducts.
     * A method call to collectProducts is made to get a list of all productIds in this storage.
     * Creates a productList with all products in the database.
     * Creates a totalStorageProduct list.
     * The for loop and for each loop is made to compare all storageProductIds with all products in the database to add them
     * in a the returned list called totalStorageProducts.
     */
    //TODO change createSessionFactory to getSessionFactory
    public List<Product> sortProducts() {
        Session session = new SessionFactoryCfg().createSessionFactory().openSession();

        List<StorageProductController> storageProducts = collectProducts();
        List<Product> productList = session.createQuery("FROM Product").list();
        List<Product> totalStorageProducts = new ArrayList<>();

        for (int i = 0; i < storageProducts.size(); i++) {
            for (Product product : productList) {
                if (product.getId() == storageProducts.get(i).getProductId()) {
                    totalStorageProducts.add(product);
                }
            }
        }
        totalStorageProducts.sort(Comparator.comparing(Product::getName));
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

        for (int i = 0; i < storageProducts.size(); i++) {
            totalPrice = totalPrice.add(storageProducts.get(i).priceOfAllBatches(this));
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return name;
    }
}
