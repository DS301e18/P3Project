package controller;

import model.Employee;
import model.Restaurant;

import relationClasses.RestaurantEmployee;
import relationClasses.RestaurantStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/EPopUp")
public class EmployeePopUpController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Input parameters
        String employeeFirstName = req.getParameter("newEmployeeFirstName");
        String employeeLastName = req.getParameter("newEmployeeLastName");
        String employeeUsername = req.getParameter("newEmployeeUsername");
        String employeePassword = req.getParameter("newEmployeePassword");

        //Only execute if all text fields has input
        if(!employeeFirstName.equals("") && !employeeLastName.equals("") && !employeeUsername.equals("") && !employeePassword.equals("")){
            HttpSession session = req.getSession();

            Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
            Employee employee = new Employee(employeeUsername, employeePassword,employeeFirstName,employeeLastName, "Medarbejder");
            new RestaurantEmployee(restaurant.getId(), employee.getId());

            session.setAttribute("showEPopUp", false);
        }

        resp.sendRedirect("webpanel.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("showEPopUp", true);
        resp.sendRedirect("webpanel.jsp");
    }
}
