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

        <form action="${pageContext.request.contextPath}/fc/addToCart-command" method="post">
            <div class="row">
                <div class="col">
                    <label class="form-label" for="top-selection">Topping</label>
                    <select id="top-selection" name="topping" class="form-select" required>
                        <option disabled selected>Vælg en topping</option>
                        <c:forEach items="${applicationScope.toppings}" var="topping">
                            <option value="${topping.getId()}">${topping.getEntity().getComponentName()} - DKK ${topping.getEntity().getComponentPrice()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <label class="form-label" for="bottom-selection">Bund</label>
                    <select id="bottom-selection" name="bottom" class="form-select" required>
                        <option disabled selected>Vælg en bund</option>
                        <c:forEach items="${applicationScope.bottoms}" var="bottom">
                            <option value="${bottom.getId()}">${bottom.getEntity().getComponentName()} - DKK ${bottom.getEntity().getComponentPrice()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <label class="form-label" for="quantity">Antal</label>
                    <input type="number" class="form-control" id="quantity" min="1" max="99" name="quantity" required>
                </div>
                <div class="col">
                    <label class="form-label"></label>
                    <input class="btn btn-primary" type="submit">
                </div>
            </div>
        </form>



    </jsp:body>
</t:pagetemplate>