package controller;

import model.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/EditStorageController")
public class EditStorageController extends HttpServlet {

    /** Edit storage information if edit button pressed*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Get parameters
        String storageName = req.getParameter("editStorageName");

        //Can't edit a storage if it has nothing written in the text field
        if(!storageName.equals("")){
            //Get current attributes
            HttpSession session = req.getSession();
            Storage storage = (Storage) session.getAttribute("storageChosen");
            storage.setName(storageName);
            storage.update();

            //Close popup
            session.setAttribute("showEditSPopUp", false);
        }

        resp.sendRedirect("webpanel.jsp");
    }

    /** If button pressed, show popup*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("showEditSPopUp", true);
        resp.sendRedirect("webpanel.jsp");
    }
}
