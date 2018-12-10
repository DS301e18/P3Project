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

<section class="employeesection">
    <div class="contentBox" id="inventoryHeader">

        <!--Employee button name -->
        <label>Medarbejdere</label>

        <!-- Edit button -->
        <%if(session.getAttribute("role").equals("Chef")){%>
        <form action="EditStorageController" method="get">
            <button><span style="font-size: 20px"><i class="fas fa-hammer"></i></span></button>
        </form>
        <%}%>
    </div>

<%Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");%>
    <div id="employees"><%
        List<Employee> employeesList = (List<Employee>) session.getAttribute("employeeList");

        List<Employee> employeesShownList = new ArrayList<>();

        if(employeesList == null){
            employeesList = restaurant.sortEmployees();
            session.setAttribute("employeesList", employeesList);
        }%>
        <form name="employeeChosenEvent" action="Employee" method="post">
            <input type="hidden" name="employeeChosenButton"><%
            int i = 0;
            for(Employee employee : employeesList){
                employeesShownList.add(employee);%>
            <button class="employeeButton" onclick="employeeChoice(id)" value="<%=i%>" id="<%=employee.getId()%>">
                <label><%=employee.getFirstName()%></label>
            </button><%
                i++;
            }
            session.setAttribute("employeesListForChoosing", employeesShownList);%>
        </form>


    </div>

    <div class="priceBox">
        <!--Search Bar-->
        <form action="search" accept-charset="ISO-8859-1" method="post">
            <input type="text" placeholder="Søg..." name="search" style="
    margin-left: 40px;">
        </form>
    </div>
    </div>
</section>

<script>
    function employeeChoice(employeeID) {
        var button = document.getElementById(employeeID).value;
        document.employeeChosenEvent.employeeChosenButton.value = button;
    }
</script>


</body>
</html>
