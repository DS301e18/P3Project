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

    StorageInitializerController storageList = new StorageInitializerController(session);

    List<Storage> storages = storageList.getStorageInfo();

    session.setAttribute("storages", storages);
%>

<!-- Side navigation (storage navigation)-->
<div class="sideNav">

    <form name="storageChoser" action="Storage" method="post">
        <input type="hidden" name="buttonChosen">
    <%
        for (Storage storage : storages) {%>
            <button class="menuDot" onclick="storageChoice(id)" value="<%=storage.getId()%>" id="<%=storage.getId()%>">
                <%=storage.getName()%>
            </button>

    <%}%>
    </form>
    <button><i class="fas fa-plus-circle"></i></button>

</div>


<script>
    function storageChoice(storageID) {
        document.storageChoser.buttonChosen.value = document.getElementById(storageID).value;
        x = document.storageChoser.buttonChosen.value;
    }
</script>


</body>
</html>
