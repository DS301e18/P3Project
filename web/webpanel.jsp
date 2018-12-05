<%@ page import="model.Storage" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.SearchController" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="model.SessionFactoryCfg" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="controller.StorageInitializerController" %><%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 26/11/2018
  Time: 18.49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Storage Menu</title>

    <!-- Adjust after the size of the device -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Import Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="style/storageMenu.css">

</head>
<body>

<%
    //Assures that the user can't go back after logout (removes cache)
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1
    response.setHeader("Pragma", "no-cache");// HTTP 1.0
    response.setHeader("Expires", "0");// Proxies

    StorageInitializerController storageList = new StorageInitializerController(session);

    //Make so all storages are available in all .jsp files so they aren't needed to be reloaded in every file
    List<Storage> storages = storageList.getStorageInfo();
    session.setAttribute("storages", storages);
%>

    <jsp:include page="navigation.jsp"/>
    <jsp:include page="sidebar.jsp"/>

    <%
        if(session.getAttribute("storageChosen") != null){%>

            <jsp:include page="storageInventory.jsp"/>
        <%}

    %>

</body>
</html>
