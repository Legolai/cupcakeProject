<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<%@ page import="dk.cphbusiness.dat.cupcakeproject.control.GetAllUsersServlet"%>

<t:pagetemplate>
    <jsp:attribute name="header">
            Bruger liste
    </jsp:attribute>

    <jsp:attribute name="footer">
            Bruger liste
    </jsp:attribute>

    <jsp:body>

        <h3>Her er listen over alle bruger</h3>

        <jsp:include page="getAllUsers" flush="true" />
        <%-- <c:out value="${users }"></c:out> --%>
        <p>this is userid 2: "${sessionScope.userList.get(0).entity.name}".</p>
        <p>this is userid 3: "${sessionScope.userList.get(1).entity.name}".</p>


    </jsp:body>
</t:pagetemplate>