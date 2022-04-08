<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>
<%@ page import="dk.cphbusiness.dat.cupcakeproject.model.entities.Role"%>

<t:pagetemplate>
    <jsp:attribute name="header">
         Olsker cupcakes
    </jsp:attribute>

    <jsp:attribute name="footer">
        Welcome to the frontpage
    </jsp:attribute>

    <jsp:body>

        <p>Startcode for 2nd semester </p>

        <c:if test="${sessionScope.user != null}">
            <p>You are logged in with the role of "${sessionScope.user.role}".</p>


            <c:if test="${sessionScope.user.role.equals(Role.ADMIN)}">
                <br>
                <p>Klik her for at se alle bruger <a
                        href="adminUserList.jsp">Bruger liste</a></p>
                <br>
                <p>Klik her for at se alle Ordre <a
                        href="adminOrderList.jsp">Ordre liste</a></p>


            </c:if>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>You are not logged in yet. You can do it here: <a
                    href="login.jsp">Login</a></p>

            <br>
            <p>Opret en bruger: <a
                    href="createAccount.jsp">Opret Bruger</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>