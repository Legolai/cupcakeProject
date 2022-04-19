<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" %>
<%@ page import="dk.cphbusiness.dat.cupcakeproject.model.entities.Role"%>

<t:pagetemplate>
    <jsp:attribute name="header">
         Olsker cupcakes
    </jsp:attribute>

    <jsp:attribute name="footer">
        Welcome to the frontpage
    </jsp:attribute>

    <jsp:body>

        <p>Start code for 2nd semester </p>

        <c:if test="${sessionScope.user != null}">
            <p>You are logged in with the role of."${sessionScope.user.getEntity().getRole()}"</p>

            <c:if test="${sessionScope.user.getEntity().getRole().equals(Role.ADMIN)}">
                <br>
                <p>Klik her for at gå til admin siden <a
                        href="${pageContext.request.contextPath}/fc/admin-page">Admin side</a></p>
            </c:if>

            <c:if test="${sessionScope.user.getEntity().getRole().equals(Role.CUSTOMER)}">
                <br>
                <p>Klik her for at gå til din bruger side <a
                        href="${pageContext.request.contextPath}/fc/account-page">Bruger side</a></p>
            </c:if>

        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="${pageContext.request.contextPath}/fc/login-page">Login</a></p>

            <br>
            <p>Opret en bruger: <a
                    href="${pageContext.request.contextPath}/fc/register-page">her</a></p>
        </c:if>


    </jsp:body>

</t:pagetemplate>