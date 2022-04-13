<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<script>
    function toggleForm() {
        var x = document.getElementById("changeUserForm");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
</script>

<t:pagetemplate>
    <jsp:attribute name="header">
             Bruger side
    </jsp:attribute>

    <jsp:attribute name="footer">
            Bruger side
    </jsp:attribute>

    <jsp:body>

        <h3>Personlig information: </h3>
        <p>Navn: ${sessionScope.user.getEntity().getName()}</p>
        <p>Email: ${sessionScope.user.getEntity().getEmail()}</p>
        <p>Addresse: ${sessionScope.user.getEntity().getAddress()}</p>

        <br>
        <button onclick="toggleForm()">Klik her for at ændre personlig information</button><br>
        <div id="changeUserForm" style="display: none">
            <c:set var="updateUser" value="${sessionScope.user}" scope="session"/>
            <form action="${pageContext.request.contextPath}/fc/updateUser-command" method="post">
                <label for="updateName">Navn: </label>
                <input type="text" id="updateName" name="updateName" value=${sessionScope.user.getEntity().getName()}>
                <br><label for="updateEmail">Email: </label>
                <input type="text" id="updateEmail" name="updateEmail" value=${sessionScope.user.getEntity().getEmail()}>
                <br><label for="updateAddress">Addresse: </label>
                <input type="text" id="updateAddress" name="updateAddress" value=${sessionScope.user.getEntity().getAddress()}>
                <input type="submit"  value="Gem"/>
            </form>
        </div>




        <br><br>
        <h3>Her er listen over hvilke ordre du har: </h3>




    </jsp:body>
</t:pagetemplate>