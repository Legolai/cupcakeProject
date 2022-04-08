<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
            Opret Bruger
    </jsp:attribute>

    <jsp:attribute name="footer">
            Opret Bruger
    </jsp:attribute>

    <jsp:body>

        <h3>Opret Bruger</h3>

        <form action="createAccount" method="post">
            <label for="name">Navn: </label>
            <input type="text" id="name" name="name"/>
            <label for="email">Email: </label>
            <input type="text" id="email" name="email"/>
            <label for="password">Kode ord: </label>
            <input type="password" id="password" name="password"/>
            <input type="submit"  value="Opret bruger"/>
        </form>

    </jsp:body>
</t:pagetemplate>