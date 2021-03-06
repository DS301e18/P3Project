package controller.SuperuserController;

import model.Restaurant;
import model.SystemAdministrator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Restaurant")
public class RestaurantController extends HttpServlet {

    /** Add new restaurant */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("newRestaurant");

        new Restaurant(name);

        resp.sendRedirect("superuserWebpanel.jsp");

    }

    /** Delete Restaurant */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int restaurantID = Integer.parseInt(req.getParameter("resIDDelete"));

        List<Restaurant> restaurantList = SystemAdministrator.collectRestaurants();
        Restaurant restaurant = restaurantList.get(restaurantID);

        restaurant.removeRestaurant();

        resp.sendRedirect("superuserWebpanel.jsp");
    }
}
