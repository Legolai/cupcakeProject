<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" %>


<script>
    function toggleCupcakeTable() {
        const x = document.getElementById("cupcakeTable");
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
        <c:if test="${sessionScope.cart.getCartItems().size() == 0}">
            <p>Kurven er tom</p>
            <a class="btn btn-primary disabled" href="${pageContext.request.contextPath}/fc/checkout-command" >Check ud</a>
        </c:if>
        <c:if test="${sessionScope.cart.getCartItems().size() != 0}">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Top</th>
                        <th scope="col">Bund</th>
                        <th scope="col">Kommentar</th>
                        <th scope="col">Antal</th>
                        <th scope="col">Stk. pris</th>
                        <th scope="col">Pris</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <form>
                        <c:forEach varStatus="loop" items="${sessionScope.cart.getCartItems()}" var="cartItem">
                            <tr>
                                <th scope="row">${loop.index+1}</th>
                                <td>${cartItem.getTopping().getEntity().getComponentName()}</td>
                                <td>${cartItem.getBottom().getEntity().getComponentName()}</td>
                                <td>${cartItem.getComments()}</td>
                                <td>${cartItem.getQuantity()}</td>
                                <td>${cartItem.getPiecePrice()}</td>
                                <td>${cartItem.getTotalPrice()}</td>
                                <td><button type="submit" formaction="${pageContext.request.contextPath}/fc/removeFromCart-command" formmethod="post" value="${loop.index}" name="remove" class="btn btn-danger"> Fjern</button></td>
                            </tr>
                        </c:forEach>
                    </form>
                </tbody>
                <tfoot>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Total pris:</td>
                        <td>${sessionScope.cart.getTotalPrice()}</td>
                        <td></td>
                    </tr>
                </tfoot>
            </table>

            <form action="${pageContext.request.contextPath}/fc/checkout-command" method="post">
                <label for="dateTime">Pick up dato og tid</label>
                <input id="dateTime" name="requestedDeliveryDate" type="datetime-local" required>
                <button type="submit" class="btn btn-primary">Check ud</button>
            </form>


        </c:if>







    </jsp:body>
</t:pagetemplate>