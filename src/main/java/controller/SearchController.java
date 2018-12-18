package controller;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {

    /** Controller for when the user search for a specific product*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input parameter
        String input = req.getParameter("search");

        //Get current storage
        HttpSession session = req.getSession();
        Storage storage = (Storage) session.getAttribute("storageChosen");

        //Sorted list
        List<Product> productList = new Search().searchProduct(storage, input);

        //Update shown product list
        session.setAttribute("productList", productList);

        resp.sendRedirect("webpanel.jsp");

    }

    /** Search for a specific employee*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String input = req.getParameter("search");

        HttpSession session = req.getSession();
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");

        List<Employee> employeeList = new Search().searchEmployee(restaurant, input);

        session.setAttribute("employeeList", employeeList);
        resp.sendRedirect("webpanel.jsp");
    }
}