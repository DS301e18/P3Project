<%@ page import="java.util.List" %>
<%@ page import="model.Storage" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="model.SessionFactoryCfg" %>
<%@ page import="org.hibernate.Transaction" %>
<%@ page import="org.hibernate.HibernateException" %>
<%@ page import="controller.AssignedEmployeesController" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page import="model.Employee" %>
<%@ page import="controller.AssignedStorageController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="controller.StorageInitializerController" %>
<%--
  Created by IntelliJ IDEA.
  User: Kristoffer
  Date: 30/11/2018
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //Assures that the user can't go back after logout (removes cache)
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1
    response.setHeader("Pragma", "no-cache");// HTTP 1.0
    response.setHeader("Expires", "0");// Proxies

    StorageInitializerController storageList = new StorageInitializerController(session);
%>

<!-- Side navigation (storage navigation)-->
<div class="sideNav">

    <%for (Storage storage : storageList.getStorageInfo()) {%>
        <button><div class="menuDot"><a><%=storage.getName()%></a></div></button>
    <%}%>
    <button><i class="fas fa-plus-circle"></i></button>

</div>


</body>
</html>
