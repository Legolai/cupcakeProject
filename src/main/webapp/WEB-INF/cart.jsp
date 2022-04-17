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
            Indkøbskurv
    </jsp:attribute>

    <jsp:attribute name="footer">
            Indkøbskurv
    </jsp:attribute>

    <jsp:body>

        <h3>
            Her er din indkøbskurv:
        </h3>


        <a href="${pageContext.request.contextPath}/fc/insertOrder-command">Check ud</a>


    </jsp:body>
</t:pagetemplate>