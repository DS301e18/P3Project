<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Restaurant" %>
<%@ page import="relationClasses.RestaurantEmployee" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="util.SessionFactoryCfg" %>
<%@ page import="java.rmi.server.RemoteStub" %>
<%@ page import="model.Employee" %>
<%@ page import="model.Transactions" %>

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
    <section>
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
            <button class="tab" id="Addemployee" style="width: 150px">Tilføj medarbejder</button>
        </form>

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

    <%if(session.getAttribute("employeeChosen") != null){%>
        <aside>
            <%Employee employee = (Employee) session.getAttribute("employeeChosen");%>
            <!-- Product information header -->
            <div class="productHeader">
                <!-- Only on mobile platform, in order to close product information -->
                <button style="float: right;" onclick="hide('productInformation')"><i class="fas fa-times"></i></button>

                <form action="" method="get">
                    <label style="font-size: 40px"><%=employee.getFirstName() + " "+ employee.getLastName()%></label>
                    <!-- Edit button -->
                    <button><span style="font-size: 30px"><i class="fas fa-hammer"></i></span></button>
                </form>
            </div>

            <table class="productTable">
                <!-- History table header -->
                <tr>
                    <th>Lager</th>
                    <th>Dato</th>
                    <th>Vare</th>
                    <th>Batch nr.</th>
                    <th>Type</th>
                    <th>Antal</th>
                </tr>
            <%  List<Transactions> employeeHistory = (List<Transactions>) session.getAttribute("history");
                for (Transactions transaction : employeeHistory){%>
                <tr>
                    <td><%=transaction.getStorageName()%></td>
                    <td><%=transaction.getTimestamp()%></td>
                    <td><%=transaction.getProduct()%></td>
                    <td><%=transaction.getBatch()%></td>
                    <td><%=transaction.getTranstype()%></td>
                    <td><%=transaction.getAmount()%></td>
                </tr>
                <%}%>

            </table>
        </aside>
    <%}%>
</div>
<script>
    function employeeChoice(employeeID) {
        var button = document.getElementById(employeeID).value;
        document.employeeChosenEvent.employeeChosenButton.value = button;
    }
</script>
</body>
</html>
