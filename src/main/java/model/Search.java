package model;


import java.util.ArrayList;
import java.util.List;

public class Search {

    List<Product> productList = new ArrayList<>();

    public List<Employee> searchEmployee(Restaurant restaurant, String input){

        List<Employee> employeeList = new ArrayList<>();

        for (Employee employee : restaurant.sortEmployees()){

            String name = employee.getFirstName() + " " + employee.getLastName();

            if(input != null){
                if(name.toLowerCase().contains(input.toLowerCase())){
                    employeeList.add(employee);
                }
            }
        }

        return employeeList;
    }

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