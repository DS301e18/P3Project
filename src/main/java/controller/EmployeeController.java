package controller;

import model.Employee;
import model.History;
import model.Restaurant;
import model.Transactions;

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


    /** If menu icon has been pressed */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        Restaurant currentRestaurant = (Restaurant) session.getAttribute("restaurant");

        List<Employee> employee = currentRestaurant.sortEmployees();

        session.setAttribute("employeeList", employee);
        session.setAttribute("employeeMenu",true);
        session.setAttribute("storageChosen", null);

        response.sendRedirect("webpanel.jsp");

    }

    /** Redirect to the chosen employees informations*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Parameter with employee ID
        int employeeID = Integer.parseInt(req.getParameter("employeeChosenButton"));

        //Get list of employees in current restaurant
        HttpSession session = req.getSession();
        List<Employee> employees = (List) session.getAttribute("employeesListForChoosing");
        Employee employee = employees.get(employeeID);

        History history = new History();
        List<Transactions> employeeHistory = history.readEmployeeHistory(employee.getId());


        //Attribute employeeChosen to the chosen employee
        session.setAttribute("employeeChosen", employee);
        session.setAttribute("history", employeeHistory);

        resp.sendRedirect("webpanel.jsp");

    }
}