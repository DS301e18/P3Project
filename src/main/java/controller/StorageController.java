package controller;

import model.Restaurant;
import model.Storage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import relationClasses.RestaurantEmployee;
import relationClasses.RestaurantStorage;
import util.SessionFactoryCfg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Storage")
public class StorageController extends HttpServlet{

    /** Show chosen storage */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Parameter with storage ID
        int storageID = Integer.parseInt(request.getParameter("buttonChosen"));

        //Get list of storages in current restaurant
        HttpSession session = request.getSession();
        List<Storage> storages = (List) session.getAttribute("storages");

        //Attribute storageChosen to the chosen storage
        session.setAttribute("storageChosen", storages.get(storageID));

        //If product information is open, close it
        session.setAttribute("productChosen", null);

        response.sendRedirect("webpanel.jsp");

    }


    /** Delete Storage **/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Get current storage
        HttpSession session = req.getSession();
        Storage storage = (Storage) session.getAttribute("storageChosen");

        //Delete storage from database
        storage.remove();

        //Find relation to the restaurant
        Session hibSession = new SessionFactoryCfg().getSessionFactory().openSession();
        Query relation = hibSession.createQuery("From RestaurantStorage where storageId =:i");
        relation.setParameter("i", storage.getId());
        RestaurantStorage relationElement = (RestaurantStorage) relation.uniqueResult();

        //Delete relation from database
        relationElement.remove();

        hibSession.close();

        //Close popup
        session.setAttribute("showEditSPopUp", false);

        //Close storage inventory page
        session.setAttribute("storageChosen", null);

        resp.sendRedirect("webpanel.jsp");

    }
}
