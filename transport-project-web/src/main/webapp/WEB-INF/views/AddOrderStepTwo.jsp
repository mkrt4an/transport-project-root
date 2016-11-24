<%@include file="header.jsp" %>

<h1>Assign truck to order</h1><br>


<form action="/order/add/set-truck-confirm" method="get">
    <input type="hidden" name="orderId" value="${orderId}" />

    <%--uid:<br>--%>
    <%--<input type="text" name="uid" value="${order.uid}"/><br><br>--%>

    <%--status:<br>--%>
    <%--<input type="text" name="status" value="${order.status}"/><br>--%>

    <%--cargo:<br>--%>
    <%--<select name="cargoId">--%>
        <%--<option disabled selected value> -- select an option -- </option>--%>
        <%--<c:forEach var="item" items="${cargoAll}">--%>
            <%--<option value="${item.id}"--%>
                <%--&lt;%&ndash;<c:if test="${item.regNumber==order.currentTruck.regNumber}">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<c:out value="selected"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
            <%-->--%>
                <%--<c:out value="${item.name}"/>--%>
            <%--</option>--%>
        <%--</c:forEach>--%>
    <%--</select><br><br>--%>

    <%--from city:<br>--%>
    <%--<select name="fromCityId">--%>
        <%--<option disabled selected value> -- select an option -- </option>--%>
        <%--<c:forEach var="item" items="${cityAll}">--%>
            <%--<option value="${item.id}"--%>
                <%--&lt;%&ndash;<c:if test="${item.regNumber==order.currentTruck.regNumber}">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<c:out value="selected"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
            <%-->--%>
                <%--<c:out value="${item.name}"/>--%>
            <%--</option>--%>
        <%--</c:forEach>--%>
    <%--</select><br><br>--%>

    <%--to city:<br>--%>
    <%--<select name="toCityId">--%>
        <%--<option disabled selected value> -- select an option -- </option>--%>
        <%--<c:forEach var="item" items="${cityAll}">--%>
            <%--<option value="${item.id}"--%>
                <%--&lt;%&ndash;<c:if test="${item.regNumber==order.currentTruck.regNumber}">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<c:out value="selected"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
            <%-->--%>
                <%--<c:out value="${item.name}"/>--%>
            <%--</option>--%>
        <%--</c:forEach>--%>
    <%--</select><br><br>--%>


    currentTruck:<br>
    <select name="truckId">
        <option disabled selected value> -- select an option -- </option>
        <c:forEach var="item" items="${truckAll}">
            <option value="${item.id}"
                    <%--<c:if test="${item.regNumber==order.currentTruck.regNumber}">--%>
                        <%--<c:out value="selected"/>--%>
                    <%--</c:if>--%>
                    >
                <c:out value="${item.regNumber}"/>
            </option>
        </c:forEach>
    </select><br><br>

    <%--driverList:<br>--%>
        <%--<c:forEach var="item" items="${driverAll}">--%>
            <%--<input type="checkbox" name="driver${item.id}" value="${item.id}">--%>
                <%--<c:out value="${item.firstName}"/><br>--%>
        <%--</c:forEach>--%>
    <%--</select><br><br>--%>


    <input type="submit" name="update" value="submit"/>
    <input type="button" name="Cancel" value="Back" onclick="location.href='/GetAllOrdersServlet'"/>
</form>

<%@include file="footer.jsp" %>