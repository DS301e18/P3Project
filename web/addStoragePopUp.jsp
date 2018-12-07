<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 07/12/2018
  Time: 11.08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tilføj nyt lager</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"
          charset="UTF-8">
</head>
<body>
    <div class="popUp">
        <header id="popUpHeader">Tilføj Lager</header>

        <div id="popUpBody">
            <form action="SPopUp" accept-charset="ISO-8859-1" method="post">
                <input type="text" placeholder="Angiv navnet på lageret..." name="newStorage"><br>
                <input style="float: right; margin-right: 150px;" type="submit" value="Registrer">
            </form>

            <form action="ClosePopUp" method="get">
                <input style="float: left; margin-left: 150px;" type="submit" value="Annuller">
            </form>
        </div>
    </div>
</body>
</html>
