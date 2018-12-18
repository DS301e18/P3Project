package controller;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import relationClasses.RestaurantEmployee;
import util.SessionFactoryCfg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DeleteEmployee")
public class DeleteEmployeeController extends HttpServlet {

    /** Delete an employee */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Employee employee = (Employee) session.getAttribute("employeeChosen");
        employee.removeEmployee();

        Session hibSession = SessionFactoryCfg.getSessionFactory().openSession();

        Query relation = hibSession.createQuery("From RestaurantEmployee where employeeId=:i");
        relation.setParameter("i", employee.getId());
        RestaurantEmployee relationElement = (RestaurantEmployee) relation.uniqueResult();
        relationElement.remove();

        hibSession.close();

        session.setAttribute("showEditEmployee", false);
        session.setAttribute("employeeChosen", null);

        resp.sendRedirect("webpanel.jsp");
    }
}
