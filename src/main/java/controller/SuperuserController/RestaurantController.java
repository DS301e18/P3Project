package controller.SuperuserController;

import model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Restaurant")
public class RestaurantController extends HttpServlet {

    /** Add new restaurant */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("newRestaurant");

        new Restaurant(name);

        resp.sendRedirect("superuserWebpanel.jsp");

    }
}
