package controller;

import model.Restaurant;
import model.Storage;
import relationClasses.RestaurantStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SPopUp")
public class StoragePopUpController extends HttpServlet {

    /** Add Storage */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input parameter
        String storageName = req.getParameter("newStorage");

        //Find current restaurant
        HttpSession session = req.getSession();
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");

        //Add storage to database
        Storage storage = new Storage(storageName);
        new RestaurantStorage(restaurant.getId(), storage.getId());

        //Close popup
        session.setAttribute("showSPopUp", false);

        resp.sendRedirect("webpanel.jsp");
    }

    /** Open pop up*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("showSPopUp", true);
        resp.sendRedirect("webpanel.jsp");
    }
}
