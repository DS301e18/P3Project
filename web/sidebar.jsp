<%@ page import="model.Storage" %>
<%@ page import="controller.StorageInitializerController" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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

    List<Storage> storages = (List) session.getAttribute("storages");
%>

<!-- Side navigation (storage navigation)-->
<div class="sideNav">

    <!-- Generate storage buttons -->
    <form name="storageChooser" action="Storage" method="post">
        <input type="hidden" name="buttonChosen">
        <%
            int i = 0;
            for (Storage storage : storages) {%>
                <button class="menuDot" onclick="storageChoice(id)" value="<%=i%>" id="<%=storage.getId()%>">
                    <%=storage.getName()%>
                </button><%
                i++;
            }%>
    </form>

    <!-- Add a new storage to the restaurant-->
    <button><i class="fas fa-plus-circle"></i></button>

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
