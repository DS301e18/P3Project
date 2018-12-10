package controller.SuperuserController;


import model.Employee;
import model.Manager;
import model.SystemAdministrator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int managerID = Integer.parseInt(req.getParameter("manIDDelete"));

        List<Manager> managerList = SystemAdministrator.collectManagers();

        Manager manager = managerList.get(managerID);

        manager.removeEmployee();

        resp.sendRedirect("superuserWebpanel.jsp");
    }
}
