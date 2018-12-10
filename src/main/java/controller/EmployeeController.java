package controller;

import model.Employee;
import model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Employee")
public class EmployeeController extends HttpServlet{

    /** */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Restaurant currentRestaurant = (Restaurant) session.getAttribute("restaurant");

        List<Employee> employee = currentRestaurant.sortEmployees();

        session.setAttribute("employeeList", employee);

        session.setAttribute("storageChosen", null);

        response.sendRedirect("webpanel.jsp");

    }
}