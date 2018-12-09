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
    <title>Title</title>
    <section class="employeesection">
        <div class="contentBox" id="inventoryHeader">

            <!--Storage name-->
            <label>Medarbejdere</label>

            <!-- Edit button -->
            <%if(session.getAttribute("role").equals("Chef")){%>
            <form action="EditStorageController" method="get">
                <button><span style="font-size: 20px"><i class="fas fa-hammer"></i></span></button>
            </form>
            <%}%>
        </div>

        <div id="inventory"><%

            Restaurant currentResturant = new Restaurant();

            //List<RestaurantEmployee> employeesShown;

          //  employeesShown = currentResturant.collectEmployees();

            System.out.println(currentResturant.getName());

           %>


        </div>

        <div class="priceBox"><a>Total pris: 69 kr.</a></div>
    </section>

</head>
<body>

</body>
</html>
