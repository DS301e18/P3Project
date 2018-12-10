<%@ page import="model.Storage" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.StorageInitializerController" %>
<%@ page import="controller.EmployeeController" %><%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 26/11/2018
  Time: 18.49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <base href="${pageContext.request.contextPath}">
    <title>Storage Menu</title>

    <!-- Adjust after the size of the device -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Import Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
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

<%if ((boolean) session.getAttribute("showSPopUp")) {%>
<jsp:include page="addStoragePopUp.jsp"/>
<%}%>

<%if ((boolean) session.getAttribute("showEditSPopUp")) {%>
<jsp:include page="editStoragePopUp.jsp"/>
<%}%>

<%if ((boolean) session.getAttribute("showEditProPopUp")) {%>
<jsp:include page="editProductPopUp.jsp"/>
<%}%>

<%if ((boolean) session.getAttribute("showEPopUp")) {%>
<jsp:include page="addEmployeePopUp.jsp"/>
<%}%>


<%if (session.getAttribute("storageChosen") != null) {%>
<jsp:include page="storageInventory.jsp"/>
<%}%>

<%
    if ((boolean)session.getAttribute("employeeMenu")) {%>
<jsp:include page="employee.jsp"/>
<%
    }

%>



</body>
</html>
