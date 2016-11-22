<%@include file="header.jsp" %>

<h1>Update order page</h1><br>

<form action="/UpdateOrderServletConfirmed" method="get">
    <input type="hidden" name="id" value="${order.id}"/>

    Uid:<br>
    <input type="text" name="uid" required value="${order.uid}"/><br><br>

    Status:<br>
    <input type="text" name="status" required value="${order.status}"/><br><br>

    CurrentTruck:<br>
    <select name="currentTruck" required>
        <option disabled selected value> -- select an option --</option>
        <c:forEach var="item" items="${truckAll}">
            <option value="${item.id}"
                    <c:if test="${item.regNumber==order.currentTruck.regNumber}">
                        <c:out value="selected"/>
                    </c:if>>
                <c:out value="${item.regNumber}"/>
            </option>
        </c:forEach>
    </select><br><br>

    <input type="submit" name="update" value="submit"/>
    <input type="button" name="Cancel" value="Back" onclick="location.href='/GetAllOrdersServlet'"/>
</form>

<%@include file="footer.jsp" %>