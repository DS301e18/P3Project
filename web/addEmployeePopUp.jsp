<%--
  Created by IntelliJ IDEA.
  User: Andreh
  Date: 10-12-2018
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tilføj ny medarbejder</title>
</head>
<body>
    <div class="popUp">
        <header id="popUpHeader"><label>Restaurant</label></header>

        <div id="popUpBodyEmployee">
            <form action="EPopUp" accept-charset="ISO-8859-1" method="post">
                <input style="margin: 5px" type="text" placeholder="Angiv fornavn på medarbejderen..." name="newEmployeeFirstName">
                <input style="margin: 5px"  type="text" placeholder="Angiv efternavn på medarbejderen..." name="newEmployeeLastName">
                <input style="margin: 5px"  type="text" placeholder="Angiv brugernavn på medarbejderen..." name="newEmployeeUsername">
                <input style="margin: 5px"  type="text" placeholder="Angiv kodeord på medarbejderen..." name="newEmployeePassword"><br>
                <input style="float: right; margin-right: 150px;" type="submit" value="Registrer">
            </form>

        <form action="ClosePopUp" method="get">
            <input style="margin-left: 80px" type="submit" value="Annuller">
        </form>

        </div>
    </div>

</body>
</html>
