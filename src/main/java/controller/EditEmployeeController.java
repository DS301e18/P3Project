package controller;

import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/EditEmployee")
public class EditEmployeeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("editUsername");
        String password = req.getParameter("editPassword");
        String firstName = req.getParameter("editFirstName");
        String lastName = req.getParameter("editLastName");

        if(!username.equals("") && !password.equals("") && !firstName.equals("") && lastName.equals("")){
            HttpSession session = req.getSession();
            Employee employee = (Employee) session.getAttribute("employeeChosen");

            employee.setUsername(username);
            employee.setPassword(password);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);

            employee.update();

            session.setAttribute("showEditEmployee", false);
        }

        resp.sendRedirect("webpanel.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("showEditEmployee", true);
        resp.sendRedirect("webpanel.jsp");
    }
}
