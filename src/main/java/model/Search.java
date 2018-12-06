package model;


import java.util.ArrayList;
import java.util.List;

public class Search {

    List<Product> productList = new ArrayList<>();


    public List<Product> searchProduct(Storage storage, String input){

        for(Product product : storage.sortProducts()){

            if(input != null){
                if(product.getName().toLowerCase().contains(input.toLowerCase())){
                    productList.add(product);
                }
            } else {
                productList = storage.sortProducts();
            }
        }

        return productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}