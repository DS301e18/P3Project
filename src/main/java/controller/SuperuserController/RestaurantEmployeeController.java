package controller.SuperuserController;

import model.Manager;
import model.Restaurant;
import model.SystemAdministrator;
import relationClasses.RestaurantEmployee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RestaurantEmployee")
public class RestaurantEmployeeController extends HttpServlet {

    /** Employ an manager to a restaurant */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Index in the collected lists
        int restaurantID = Integer.parseInt(req.getParameter("restaurantID"));
        int managerID = Integer.parseInt(req.getParameter("managerID"));

        //Lists of all restaurants and managers
        List<Restaurant> restaurantList = SystemAdministrator.collectRestaurants();
        List<Manager> managerList = SystemAdministrator.collectManagers();

        Restaurant restaurant = restaurantList.get(restaurantID);
        Manager manager = managerList.get(managerID);

        //Assign manager to restaurant
        new RestaurantEmployee(restaurant.getId(), manager.getId());

        resp.sendRedirect("superuserWebpanel.jsp");

    }
}
