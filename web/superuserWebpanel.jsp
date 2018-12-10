<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 10/12/2018
  Time: 10.24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Velkommen Superbruger</title>

    <style>
        body {
            font-family: 'DejaVu Sans', Arial, Helvetica, sans-serif;
            font-size: 15px;
        }

        .topnav {
            overflow: hidden;
            background-color: lightblue;
            height: 50px;
            padding-bottom: 10px;
            padding-right: 10px;
        }

        button{
            background-color: lightblue;
            border: transparent;
            font-size: 20px;
        }

        .container{
            width: 100%;
            height: 100%;
            overflow: hidden;
        }

        section{
            border-right: solid lightblue;
            float: left;
            width: 50%;
            min-width: 300px;
            height: 100%;
            overflow: scroll;
        }

        aside {
            height: 100%;
            overflow: scroll;
        }

        .popUp{
            background-color: darkcyan;
            height: 300px;
            width: 600px;
            border-radius: 2%;
            margin-top: 10%;
            margin-left: 25%;
            position: absolute;
            text-align:center;
            font-size: 20px;
        }

        .popUpHeader{
            float: left;
            padding: 20px;
            color: white;
            width: 100%;
            text-align: left;
        }

        .popUpBody{
            background-color: white;
            height: 200px;
            margin: 60px 5px 5px;
            padding-top: 30px;
        }
    </style>
</head>
<body>

<%//Assures that the user can't go back after logout (removes cache)
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// HTTP 1.1
    response.setHeader("Pragma", "no-cache");// HTTP 1.0
    response.setHeader("Expires", "0");// Proxies
%>

<div class="topnav">
    <div class="topnav-right">
        <!-- Logout button -->
        <div class="formbutton">
            <form action="Login" method="get">
                <button style="float: right" type="submit" value="Logout">Log ud</button>
            </form>
        </div>
    </div>
</div>

<!-- Popup restaurant -->
<div class="popUp" id="popUpRestaurant" style="display: none">
    <header class="popUpHeader">Tilføj ny restaurant</header>

    <div class="popUpBody">
        <form action="Restaurant" accept-charset="ISO-8859-1" method="post">
            <input type="text" placeholder="Angiv navnet på Restaurant..." name="newRestaurant"><br>
            <input id="addRestaurantSubmit" type="submit" value="Registrer">
        </form>

        <button onclick="closePopUp('popUpRestaurant')">Annuller</button>
    </div>
</div>

<div class="popUp" id="popUpManager" style="display: none">
    <header class="popUpHeader">Tilføj ny restaurant</header>

    <div class="popUpBody">
        <form action="Restaurant" accept-charset="ISO-8859-1" method="post">
            <input type="text" placeholder="Fornavn..." name="managerFirstName"><br>
            <input type="text" placeholder="Efternavn..." name="managerLastName"><br>
            <input type="text" placeholder="Brugernavn..." name="username"><br>
            <input type="text" placeholder="Password" name="password"><br>
            <input id="addManagerSubmit" type="submit" value="Registrer">
        </form>

        <button onclick="closePopUp('popUpManager')">Annuller</button>
    </div>
</div>

<div class="container">
    <section>
        <label style="font-size: 30px">Restauranter</label>
        <button onclick="showPopUp('popUpRestaurant')">Tilføj restaurant</button>
    </section>

    <aside>
        <label style="font-size: 30px">Chefer</label>
        <button onclick="showPopUp('popUpManager')">Tilføj chef</button>
    </aside>
</div>

<script>
    function showPopUp(ID) {

        var showElement = document.getElementById(ID);

        if(showElement.style.display === "none"){
            showElement.style.display = "block";
        }
    }

    function closePopUp(ID) {
        var hideElement = document.getElementById(ID);

        if(hideElement.style.display === "block"){
            hideElement.style.display = "none";
        }
    }
</script>

</body>
</html>
