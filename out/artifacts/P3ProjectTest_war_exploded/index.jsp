<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 23/11/2018
  Time: 12.22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <!-- Tab title -->
        <title>Login Dalle Valle Lager</title>

        <!-- Adjust after the size of the device -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Import Stylesheet -->
        <link rel="stylesheet" type="text/css" href="../../../web/style/login.css">
    </head>

    <body>
        <div class="container">
            <!-- Icon -->
            <img src="../../../web/assets/dallevalle.png" class="center">

            <!-- Call class Login and do the "post" method -->
            <form action="Login" method="post">
                <label><b>Brugernavn:</b></label><br>
                <input type="text" placeholder="Indtast Brugernavn" name="username"><br>
                <label><b>Password:</b></label><br>
                <input type="password" placeholder="Indtast Password" name="password"><br>
                <input type="submit" value="Login">
            </form>
        </div>
    </body>
</html>
