<%@ page import="model.SystemAdministrator" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Restaurant" %>
<%@ page import="model.Manager" %>
<%@ page import="model.Employee" %><%--
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
            height: 80%;
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

        .chooseButton {
            padding-top: 10px;
            padding-bottom: 10px;
            font-size: 15px;
            width: 100%;
            text-align: left;
            text-indent: 20px;
            background-color: white;
        }

        .chooseButton:hover, .chooseButton:focus{
            background-color: lightblue;
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
    <header class="popUpHeader">Opret ny restaurant</header>

    <div class="popUpBody">
        <form action="Restaurant" accept-charset="ISO-8859-1" method="post">
            <input type="text" placeholder="Angiv navnet på Restaurant..." name="newRestaurant"><br>
            <input id="addRestaurantSubmit" type="submit" value="Opret">
        </form>

        <button onclick="closePopUp('popUpRestaurant')">Annuller</button>
    </div>
</div>

<div class="popUp" id="popUpManager" style="display: none">
    <header class="popUpHeader">Opret ny chef</header>

    <div class="popUpBody">
        <form action="Manager" accept-charset="ISO-8859-1" method="post">
            <input type="text" placeholder="Fornavn..." name="managerFirstName"><br>
            <input type="text" placeholder="Efternavn..." name="managerLastName"><br>
            <input type="text" placeholder="Brugernavn..." name="username"><br>
            <input type="text" placeholder="Password" name="password"><br>
            <input id="addManagerSubmit" type="submit" value="Opret">
        </form>

        <button onclick="closePopUp('popUpManager')">Annuller</button>
    </div>
</div>

<%List<Restaurant> restaurantList = SystemAdministrator.collectRestaurants();
    List<Manager> managerList = SystemAdministrator.collectManagers();%>

<div class="container">
    <section>
        <label style="font-size: 30px">Restauranter</label>
        <button onclick="showPopUp('popUpRestaurant')">Tilføj restaurant</button>
            <!-- Input used to know which product has been clicked and chosen-->
            <% int i = 0;
            for(Restaurant restaurant : restaurantList){%>
                <button class="chooseButton" value="<%=i%>" onclick="chosenRestaurantFunc('<%=restaurant.getName()%>', value)">
                    <label><%=restaurant.getName()%></label>
                </button><%
            i++;}%>
    </section>

    <aside>
        <label style="font-size: 30px">Chefer</label>
        <button onclick="showPopUp('popUpManager')">Tilføj chef</button>
        <!-- Input used to know which product has been clicked and chosen-->
        <% int j = 0;
            for(Manager manager : managerList){%>
                <button class="chooseButton" value="<%=j%>" onclick="chosenManagerFunc('<%=manager.getFirstName()%>','<%=manager.getLastName()%>','<%=manager.getUsername()%>','<%=manager.getPassword()%>', value)">
                    <label><%=manager.getFirstName() + " " + manager.getLastName()%></label>
                </button><%
            j++;}%>
    </aside>
    <footer style="width: 100%; height: 20%; background-color: lightblue; position: fixed; bottom: 0;">
        <div style="width: 24%; height: 100%; float: left; border-right: solid white">
            <form action="EditRestaurant" accept-charset="ISO-8859-1" method="post">
                <input id="resID" type="hidden" name="id">
                <input id="resName" type="text" name="chosenRestaurant">
                <input type="submit" value="Rediger">
            </form>
            <form action="Restaurant" method="get">
                <input id="resIDDelete" type="hidden" name="resIDDelete">
                <input type="submit" value="Slet">
            </form>
        </div>
        <div style="width: 50%; height: 100%; float: left; border-right: solid white">
            <form action="EditManager" accept-charset="ISO-8859-1" method="post">
                <input id="manID" type="hidden" name="managerID">
                <input id="manFirstName" type="text" name="fName">
                <input id="manLastName" type="text" name="lName">
                <input id="manUsername" type="text" name="uName">
                <input id="manPassword" type="text" name="pWord">
                <input type="submit" value="Rediger">
            </form>
            <form action="Manager" method="get">
                <input id="manIDDelete" type="hidden" name="manIDDelete">
                <input type="submit" value="Slet">
            </form>
        </div>
        <div style="width: 25%; height: 100%; float: left; border-right: solid white">
            <form action="RestaurantEmployee" method="post">
                <input id="restaurantID" type="hidden" name="restaurantID">
                <input id="managerID" type="hidden" name="managerID">
                <input type="submit" id="relationMapping" value="Ansæt chef til restaurant" name="relate">
            </form>
        </div>
    </footer>
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
    
    function chosenRestaurantFunc(name, value) {
        var input = document.getElementById("resName");
        var chosenID = document.getElementById("resID");
        var deleteID = document.getElementById("resIDDelete");
        var relationID = document.getElementById("restaurantID");

        input.value = name;
        chosenID.value = value;
        deleteID.value = value;
        relationID.value = value;
    }

    function chosenManagerFunc(firstName, lastName, username, password, value) {
        var fName = document.getElementById("manFirstName");
        var lName = document.getElementById("manLastName");
        var uname = document.getElementById("manUsername");
        var pword = document.getElementById("manPassword");
        var chosenID = document.getElementById("manID");
        var deleteID = document.getElementById("manIDDelete");
        var relationID = document.getElementById("managerID");

        fName.value = firstName;
        lName.value = lastName;
        uname.value = username;
        pword.value = password;
        chosenID.value = value;
        deleteID.value = value;
        relationID.value = value;
    }
</script>

</body>
</html>
