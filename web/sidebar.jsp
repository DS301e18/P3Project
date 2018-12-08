<%@ page import="model.Storage" %>
<%@ page import="java.util.List" %>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<%//Get all storages under current restaurant
    List<Storage> storages = (List) session.getAttribute("storages");
%>

<!-- Side navigation (storage navigation)-->
<div class="sideNav" id="sideNavID">

    <!-- Generate storage buttons with functionality -->
    <form name="storageChooser" action="Storage" method="post">
        <input type="hidden" name="buttonChosen"><%
            int i = 0;
            for (Storage storage : storages) {%>
                <button class="menuDot" onclick="storageChoice(id)" value="<%=i%>" id="<%=storage.getId()%>">
                    <%=storage.getName()%>
                </button><%
                i++;
            }%>
    </form>

    <!-- Add storage button -->
    <%if(session.getAttribute("role").equals("Chef")){%>
        <!-- Add a new storage to the restaurant-->
        <form action="SPopUp" method="get">
            <button><i class="fas fa-plus-circle"></i></button>
        </form>
    <%}%>
</div>


<script>
    //Parameter buttonChosen gets the same value as the storage clicked on
    function storageChoice(storageID) {
        var button = document.getElementById(storageID).value;
        document.storageChooser.buttonChosen.value = button;

        //TODO: Change the style later
        button.style.borderRadius = "25px";

    }

</script>


</body>
</html>
