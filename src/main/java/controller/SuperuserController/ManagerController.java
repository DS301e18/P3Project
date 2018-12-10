package controller.SuperuserController;


import model.Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Manager")
public class ManagerController extends HttpServlet {

    /** Add manager */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String firstName = req.getParameter("managerFirstName");
        String lastName = req.getParameter("managerLastName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        new Manager(username, password, firstName, lastName);

        resp.sendRedirect("superuserWebpanel.jsp");
    }
}
