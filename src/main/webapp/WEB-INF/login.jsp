<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" %>

<t:pagetemplate>
    <jsp:attribute name="header">
            Login
    </jsp:attribute>

    <jsp:attribute name="footer">
            Login
    </jsp:attribute>

    <jsp:body>

        <h3>You can log in here</h3>

        <form action="${pageContext.request.contextPath}/fc/login-command" method="post">
            <c:if test="${requestScope.error != null || !requestScope.equals('')}">
                <span>${requestScope.error}</span>
            </c:if>
            <label for="email">E-mail: </label>
            <input type="text" id="email" name="email"/>
            <label for="password">Password: </label>
            <input type="password" id="password" name="password"/>
            <input type="submit"  value="Log in"/>
        </form>

    </jsp:body>
</t:pagetemplate>