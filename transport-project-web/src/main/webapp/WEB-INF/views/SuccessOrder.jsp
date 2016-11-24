<%@include file="header.jsp" %>

<h1>Order draft successfully created! Assing truck and driver</h1><br>

<table border=1 width=1 cellspacing=2 cellpadding=2>
    <thead>
    </thead>
    <tbody>
    <tr>
        <th>
            <c:out value="id"/>
        </th>
        <th>
            <c:out value="uid"/>
        </th>
        <th>
            <c:out value="routePointList"/>
        </th>
        <th>
            <c:out value="driverList"/>
        </th>
        <th>
            <c:out value="currentTruck"/>
        </th>
        <th>
            <c:out value="status"/>
        </th>
        <th>
            <c:out value="update"/>
        </th>
        <th>
            <c:out value="delete"/>
        </th>
    </tr>

    <%--<c:forEach var="item" items="${list}">--%>
    <tr>
        <td>
            <c:out value="${order.id}"/>
        </td>
        <td>
            <c:out value="${order.uid}"/>
        </td>
        <td>
            <c:forEach var="item3" items="${order.routePointList}">
                <c:out value="${item3.ordinal}"/>
                <c:out value=" - "/>
                <c:out value="${item3.city.name}"/>
                <br>
            </c:forEach>
        </td>
        <td>
            <c:forEach var="item2" items="${order.driverList}">
                <c:out value="${item2.firstName}"/>
                <c:out value=" "/>
                <c:out value="${item2.lastName}"/>
                <br>
            </c:forEach>
        </td>
        <td>
            <c:out value="${order.currentTruck.regNumber}"/>
        </td>
        <td>
                <%--<c:out value="${item.status}"/>--%>
            <c:if test="${order.status == 0}"><c:out value="not done"/></c:if>
            <c:if test="${order.status == 1}"><c:out value="done"/></c:if>
        </td>
        <td>
            <a href=/order/add/set-truck?id=${order.id}>assign truck and drivers</a>
        </td>
        <td>
            <a href=/order/delete?id=${item.id} onclick="return confirm('Are you sure?')">delete</a>
        </td>

        <%--</c:forEach>--%>

    </tbody>
</table>

<%@include file="footer.jsp" %>