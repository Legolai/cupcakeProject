<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<script>
    function toggleCupcakeTable() {
        var x = document.getElementById("cupcakeTable");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
</script>

<t:pagetemplate>
    <jsp:attribute name="header">
             Cupcakes
    </jsp:attribute>

    <jsp:attribute name="footer">
            Cupcakes
    </jsp:attribute>

    <jsp:body>

        <h3>Lav din egen cupcake her</h3>

        <h3>Her er listen over alle cupcakes</h3>
        <input type="button" id="toggleCupcakesTableDisplay" value="Vis" onclick="toggleCupcakeTable();">

        <c:set var="allCupcakes" value="${true}" scope="session"/>
        <div id="cupcakeTable" style="display: none">
            <table>
                <tr>
                    <th onclick="sortTable(0)">componentName</th>
                    <th onclick="sortTable(1)">componentType</th>
                    <th onclick="sortTable(2)">componentPrice</th>
                </tr>
                <c:forEach items="${sessionScope.cupcakes}" var="cupcake">
                    <tr>
                        <td>${cupcake.entity.componentName}</td>
                        <td>${cupcake.entity.componentType}</td>
                        <td>${cupcake.entity.componentPrice}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>



    </jsp:body>
</t:pagetemplate>