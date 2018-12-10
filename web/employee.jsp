<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Restaurant" %>
<%@ page import="relationClasses.RestaurantEmployee" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="util.SessionFactoryCfg" %>
<%@ page import="java.rmi.server.RemoteStub" %>
<%@ page import="model.Employee" %>

<%--
  Created by IntelliJ IDEA.
  User: Kristoffer
  Date: 09/12/2018
  Time: 21.03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee list</title>

</head>
<body>

<!-- list of employees -->
<div class="container" id="employee">
<section class="employeesection">
    <div class="contentBox" id="inventoryHeader">
        <form action="EditStorageController" method="get">
            <!--Storage name-->
            <label>Medarbejdere</label>
        </form>
        <!--Search Bar-->
        <form action="search" accept-charset="ISO-8859-1" method="post">
            <input id="search" type="text" placeholder="Søg..." name="search">
        </form>
    </div>
    <!-- Add Employee Button -->
    <form action = "EPopUp" method="get">
    <button class="tab" id="Addemployee">Tilføj medarbejder
    </button>
    </form>
    <!-- herfra starter funktionalitet -->
    <%Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");%>
    <div id="employees"><%
        List<Employee> employeesList = (List<Employee>) session.getAttribute("employeeList");
        List<Employee> employeesShownList = new ArrayList<>();


        if (employeesList == null) {
            employeesList = restaurant.sortEmployees();
            session.setAttribute("employeesList", employeesList);
        }%>
        <form name="employeeChosenEvent" action="Employee" method="get">
            <input type="hidden" name="employeeChosenButton"><%
            int i = 0;
            for (Employee employee : employeesList) {
                employeesShownList.add(employee);%>
            <button class="productButton" onclick="employeeChoice(id)" value="<%=i%>" id="<%=employee.getId()%>">
                <label><%=employee.getFirstName()%>
                </label>
            </button>
            <%
                    i++;
                }
                session.setAttribute("employeeList", null);
                session.setAttribute("employeesListForChoosing", employeesShownList);%>
        </form>
    </div>
</section>
</div>
<script>
    function employeeChoice(employeeID) {
        var button = document.getElementById(employeeID).value;
        document.employeeChosenEvent.employeeChosenButton.value = button;
    }
</script>
</body>
</html>
