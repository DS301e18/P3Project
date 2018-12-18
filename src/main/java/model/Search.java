package model;

import java.util.ArrayList;
import java.util.List;

public class Search {

    /** Search after an employee in a specific restaurant */
    public List<Employee> searchEmployee(Restaurant restaurant, String input){

        List<Employee> employeeList = new ArrayList<>();

        for (Employee employee : restaurant.sortEmployees()){

            String name = employee.getFirstName() + " " + employee.getLastName();

            //When the program runs for te first time, input is equal null
            if(input != null){
                if(name.toLowerCase().contains(input.toLowerCase())){
                    employeeList.add(employee);
                }
            }
        }

        return employeeList;
    }

    /** Search after a product in a specific storage */
    public List<Product> searchProduct(Storage storage, String input){

        List<Product> productList = new ArrayList<>();

        for(Product product : storage.sortProducts()){

            //When the program runs for te first time, input is equal null
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
}