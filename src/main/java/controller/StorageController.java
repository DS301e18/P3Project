package controller;

import model.Storage;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int storageID = Integer.parseInt(request.getParameter("buttonChosen"))-1;

        HttpSession session = request.getSession();

        List<Storage> storages = (List) session.getAttribute("storages");

        session.setAttribute("storageChosen", storages.get(storageID));

        response.sendRedirect("webpanel.jsp");


    }
}
