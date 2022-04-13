<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<%@ page import="dk.cphbusiness.dat.cupcakeproject.model.entities.CupcakeComponentType"%>


<script>
    function toggleUserTable() {
        var x = document.getElementById("userTable");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
    function toggleOrderTable() {
        var x = document.getElementById("orderTable");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
    function toggleCupcakeTable() {
        var x = document.getElementById("cupcakeTable");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
    function sortTable(n) {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById("myTable2");
        switching = true;
        // Set the sorting direction to ascending:
        dir = "asc";
        /* Make a loop that will continue until
        no switching has been done: */
        while (switching) {
            // Start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /* Loop through all table rows (except the
            first, which contains table headers): */
            for (i = 1; i < (rows.length - 1); i++) {
                // Start by saying there should be no switching:
                shouldSwitch = false;
                /* Get the two elements you want to compare,
                one from current row and one from the next: */
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                /* Check if the two rows should switch place,
                based on the direction, asc or desc: */
                if (dir == "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir == "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                /* If a switch has been marked, make the switch
                and mark that a switch has been done: */
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                // Each time a switch is done, increase this count by 1:
                switchcount ++;
            } else {
                /* If no switching has been done AND the direction is "asc",
                set the direction to "desc" and run the while loop again. */
                if (switchcount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }
</script>

<t:pagetemplate>
    <jsp:attribute name="header">
             Admin side
    </jsp:attribute>

  <jsp:attribute name="footer">
            Admin side
    </jsp:attribute>

  <jsp:body>

    <h3>Her er listen over alle bruger</h3>

      <input type="button" id="toggleUserTableDisplay" value="Vis" onclick="toggleUserTable();"><br>
      <a href="${pageContext.request.contextPath}/fc/getAllUsers-command">opdater</a>
      <div id="userTable" style="display: none">
          <table>
              <tr><td>ID</td><td>Name</td><td>Email</td><td>Address</td><td>Phone</td>
                  <td>Role</td><td>Balance</td></tr>
              <c:forEach items="${sessionScope.allUsers}" var="user">
                  <tr>
                      <td>${user.id}</td>
                      <td>${user.entity.name}</td>
                      <td>${user.entity.email}</td>
                      <td>${user.entity.address}</td>
                      <td>${user.entity.phone}</td>
                      <td>${user.entity.role}</td>
                      <td>${user.entity.account.balance}</td>
                  </tr>
              </c:forEach>
          </table>
      </div>

      <br><br>
    <h3>Her er listen over alle ordre</h3>
      <input type="button" id="toggleOrderTableDisplay" value="Vis" onclick="toggleOrderTable();"><br>
      <a href="${pageContext.request.contextPath}/fc/getAllOrders-command">opdater</a>
      <div id="orderTable" style="display: none">
          <table>
              <tr><td>ID</td><td>userID</td><td>created</td><td>requested delivery date</td>
                  <td>shipped</td><td>is paid?</td></tr>
              <c:forEach items="${sessionScope.allOrders}" var="order">
                  <tr>
                      <td>${order.id}</td>
                      <td>${order.entity.userId}</td>
                      <td>${order.entity.created}</td>
                      <td>${order.entity.requestedDelivery}</td>
                      <td>${order.entity.shipped}</td>
                      <td>${order.entity.isPaid}</td>

                  </tr>
              </c:forEach>
          </table>
          <br>
          <p>Order detaljer: (NOTE: Der er et problem i ordermapper der gør så det rigtige antal orderDetail ikke er her)</p>
          <table>
              <c:forEach items="${sessionScope.allOrders}" var="order">
              <table>
                  <tr><td>ID</td><td>orderId</td><td>toppingId</td><td>bottomId</td>
                      <td>quantity</td><td>comments</td></tr>
                <c:forEach items="${order.entity.orderDetails}" var="orderDetails">
                  <tr>
                      <td>${orderDetails.id}</td>
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


      <br><br>
    <h3>Her er listen over alle cupcakes</h3>
      <input type="button" id="toggleCupcakesTableDisplay" value="Vis" onclick="toggleCupcakeTable();"><br>

      <c:set var="adminGetAllCupcakes" value="${true}" scope="session"/>
      <a href="${pageContext.request.contextPath}/fc/getAllCupcakes-command">opdater</a>
      <div id="cupcakeTable" style="display: none">
          <table>
              <tr>
                  <th onclick="sortTable(0)">componentName</th>
                  <th onclick="sortTable(1)">componentType</th>
                  <th onclick="sortTable(2)">componentPrice</th>
              </tr>
              <c:forEach items="${sessionScope.allCupcakes}" var="cupcake">
                  <tr>
                      <td>${cupcake.id}</td>
                      <td>${cupcake.entity.componentName}</td>
                      <td>${cupcake.entity.componentType}</td>
                      <td>${cupcake.entity.componentPrice}</td>
                  </tr>
              </c:forEach>
          </table>
      </div>

      <br><br>
    <h3>Insæt ny cupcake</h3>
      <form action="${pageContext.request.contextPath}/fc/insertCupcake-command" method="post">
          <label for="newCupcakeName">Navn: </label>
          <input type="text" id="newCupcakeName" name="newCupcakeName"/>
          <label for="newCupcakePrice">Pris: </label>
          <input type="text" id="newCupcakePrice" name="newCupcakePrice"/>
          <p>Vælg type:</p>
            <input type="radio" id="newTOPPING" name="newCupcakeType" value="TOPPING">
            <label for="newTOPPING">Top</label><br>
            <input type="radio" id="newBOTTOM" name="newCupcakeType" value="BOTTOM">
            <label for="newBOTTOM">Bund</label><br>
          <input type="submit"  value="indsæt"/>
      </form>

      <br><br>
    <h3>Lav en ændring på en cupcake</h3>
      <form action="${pageContext.request.contextPath}/fc/updateCupcake-command" method="post">
          <label for="updateCupcakeID">ID: </label>
          <input type="text" id="updateCupcakeID" name="updateCupcakeID"/>
          <label for="updateCupcakeName">Navn: </label>
          <input type="text" id="updateCupcakeName" name="updateCupcakeName"/>
          <label for="updateCupcakePrice">Pris: </label>
          <input type="text" id="updateCupcakePrice" name="updateCupcakePrice"/>
          <p>Vælg type:</p>
            <input type="radio" id="updateTOPPING" name="updateCupcakeType" value="TOPPING">
            <label for="updateTOPPING">Top</label><br>
            <input type="radio" id="updateBOTTOM" name="updateCupcakeType" value="BOTTOM">
            <label for="updateBOTTOM">Bund</label><br>
          <input type="submit"  value="indsæt"/>
      </form>



  </jsp:body>
</t:pagetemplate>