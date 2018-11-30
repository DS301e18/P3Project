<%--
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
    <link rel="stylesheet" type="text/css" href="storageMenu.css">
</head>
<body>

    <div class="sideNav">
        <button><div class="menuDot"></div></button>
        <button><div class="menuDot"></div></button>
        <button><div class="menuDot"></div></button>
    </div>
    <div class="topNav">
        <a><form action="Login" method="get">
            <button type="submit" value="Logout"><span style="font-size: 45px; color: white;"><i class="fas fa-sign-out-alt"></i></span></button>
        </form></a>
        <a1><label style="float:right">Role</label></a1>
    </div>

    <div class="container" id="storage">
        <p>Hello</p>
    </div>
</body>
</html>
