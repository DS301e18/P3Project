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
    <link rel="stylesheet" type="text/css" href="style/storageMenu.css">

</head>
<body>

    <jsp:include page="navigation.jsp" />
    <jsp:include page="sidebar.jsp"/>

    <!-- TODO: Make storage inventory dynamic -->
    <!-- Storage inventory-->
    <div class="container" id="storage">
        <section>
            <!-- Inventory header -->
            <div class="contentBox">
                <label>Storage Name</label>
                <button><span style="font-size: 20px"><i class="fas fa-hammer"></i></span></button>
            </div>
            <div class="contentBox">
                <input type="text" placeholder="Søg" name="search">
            </div>
            <button class="test"><div class="tab">Registrer Vare</div></button>
            <button class="test"><div class="tab">Historik</div></button><br>

            <!-- Inventory products -->
            <div class="productTab">
                <label>Carlsberg Sport</label>
                <label style="float: right">4</label>
            </div>
            <div class="productTab">
                <label>Pepsi Max</label>
                <label style="float: right">10</label>
            </div>

            <!-- Price-box -->
            <div class="priceBox"><a>Total pris: "Actual price" kr.</a></div>
        </section>

        <!-- Product information -->
        <aside>
            Hello
        </aside>
    </div>

</body>
</html>