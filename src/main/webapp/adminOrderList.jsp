<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<%@ page import="dk.cphbusiness.dat.cupcakeproject.control.GetAllOrdersServlet"%>

<t:pagetemplate>
    <jsp:attribute name="header">
            Ordre liste
    </jsp:attribute>

    <jsp:attribute name="footer">
            Ordre liste
    </jsp:attribute>

    <jsp:body>

        <h3>Her er listen over alle ordre</h3>

        <jsp:include page="getAllOrders" flush="true" />
        <%-- <c:out value="${users }"></c:out> --%>
        <p>this is orderid 1: "${sessionScope.orderList.get(0).entity.userId}" created: "${sessionScope.orderList.get(0).entity.created}".</p>


    </jsp:body>
</t:pagetemplate>