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

    //Add Storage
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int storageID = Integer.parseInt(request.getParameter("buttonChosen"));

        HttpSession session = request.getSession();

        List<Storage> storages = (List) session.getAttribute("storages");

        session.setAttribute("storageChosen", storages.get(storageID));

        //If product information is open, close it
        session.setAttribute("productChosen", null);

        response.sendRedirect("webpanel.jsp");


    }


    //Delete Storage
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Storage storage = (Storage) session.getAttribute("storageChosen");
        storage.remove();

        Session hibSession = new SessionFactoryCfg().getSessionFactory().openSession();

        Query relation = hibSession.createQuery("From RestaurantStorage where storageId = :i");
        relation.setParameter("i", storage.getId());
        List<RestaurantStorage> relationElement = relation.list();

        relationElement.get(0).remove();

        hibSession.close();

        session.setAttribute("showEditSPopUp", false);
        session.setAttribute("storageChosen", null);
        resp.sendRedirect("webpanel.jsp");

    }
}
