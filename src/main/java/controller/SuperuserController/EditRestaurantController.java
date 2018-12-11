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

@WebServlet("/EditRestaurant")
public class EditRestaurantController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int restaurantID = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("chosenRestaurant");

        List<Restaurant> restaurantList = SystemAdministrator.collectRestaurants();
        Restaurant restaurant = restaurantList.get(restaurantID);

        restaurant.setName(name);
        restaurant.update();

        resp.sendRedirect("superuserWebpanel.jsp");
    }
}
