<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" %>


<script>
    function toggleForm() {
        const x = document.getElementById("changeUserForm");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
    function toggleOrderTable() {
        const x = document.getElementById("orderTable");
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
        <input type="button" id="toggleOrderTableDisplay" value="Vis" onclick="toggleOrderTable();">
        <a href="${pageContext.request.contextPath}/fc/getAllOrders-command">opdater</a>
        <div id="orderTable" style="display: none">
            <table>
                <tr><td>orderId</td><td>created</td><td>requested delivery date</td>
                    <td>shipped</td><td>is paid?</td></tr>
                <c:forEach items="${requestScope.userOrders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.entity.created}</td>
                        <td>${order.entity.requestedDelivery}</td>
                        <td>${order.entity.shipped}</td>
                        <td>${order.entity.isPaid}</td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <p>Order detaljer: </p>
            <table>
                <c:forEach items="${requestScope.userOrders}" var="order">
                    <table>
                        <tr><td>orderId</td><td>toppingId</td><td>bottomId</td>
                            <td>quantity</td><td>comments</td></tr>
                        <c:forEach items="${order.getEntity().getOrderDetails()}" var="orderDetails">
                            <tr>
                                <td>${orderDetails.entity.orderId}</td>
                                <td>${orderDetails.entity.toppingId}</td>
                                <td>${orderDetails.entity.bottomId}</td>
                                <td>${orderDetails.entity.quantity}</td>
                                <td>${orderDetails.entity.comments}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:forEach>
            </table>
        </div>



    </jsp:body>
</t:pagetemplate>