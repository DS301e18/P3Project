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

@WebServlet("/EditManager")
public class EditManagerController extends HttpServlet {

    /** Edit manager */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input parameters
        int employeeID = Integer.parseInt(req.getParameter("managerID"));
        String firstName = req.getParameter("fName");
        String lastName = req.getParameter("lName");
        String username = req.getParameter("uName");
        String password = req.getParameter("pWord");

        //Only update if all text fields have a value
        if(!firstName.equals("") && !lastName.equals("") && !username.equals("") && !password.equals("")) {
            List<Manager> managerList = SystemAdministrator.collectManagers();

            Employee manager = managerList.get(employeeID);

            manager.setFirstName(firstName);
            manager.setLastName(lastName);
            manager.setUsername(username);
            manager.setPassword(password);

            manager.update();
        }

        resp.sendRedirect("superuserWebpanel.jsp");
    }
}
