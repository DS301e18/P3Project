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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String storageName = req.getParameter("editStorageName");

        HttpSession session = req.getSession();

        Storage storage = (Storage) session.getAttribute("storageChosen");

        session.setAttribute("showEditSPopUp", false);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("showEditSPopUp", true);
        resp.sendRedirect("webpanel.jsp");
    }
}
