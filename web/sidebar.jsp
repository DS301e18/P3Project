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
%>

<!-- TODO: Make dynamic -->
<!-- Side navigation (storage navigation)-->
<div class="sideNav">
    <button><div class="menuDot"></div></button>
    <button><div class="menuDot"></div></button>
    <button><i class="fas fa-plus-circle"></i></button>
</div>


</body>
</html>
