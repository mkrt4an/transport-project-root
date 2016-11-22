<%@include file="header.jsp" %>

<h1>All orders page</h1><br>

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

    <c:forEach var="item" items="${list}">
    <tr>
        <td>
            <c:out value="${item.id}"/>
        </td>
        <td>
            <c:out value="${item.uid}"/>
        </td>
        <td>
            <c:forEach var="item3" items="${item.routePointList}">
                <c:out value="${item3.city.name}"/>
                <c:out value=" - "/>
                <c:out value="${item3.ordinal}"/>
                <br>
            </c:forEach>
        </td>
        <td>
            <c:forEach var="item2" items="${item.driverList}">
                <c:out value="${item2.firstName}"/>
                <c:out value=" "/>
                <c:out value="${item2.lastName}"/>
                <br>
            </c:forEach>
        </td>
        <td>
            <c:out value="${item.currentTruck.regNumber}"/>
        </td>
        <td>
                <%--<c:out value="${item.status}"/>--%>
            <c:if test="${item.status == 0}"><c:out value="not done"/></c:if>
            <c:if test="${item.status == 1}"><c:out value="done"/></c:if>
            <c:if test="${item.status == 2}"><c:out value="draft"/></c:if>
        </td>
        <td>
            <c:if test="${item.status != 0}">
                <a href=${pageContext.request.contextPath}/order/update?id=${item.id}>update</a>
            </c:if>
        </td>
        <td>
            <c:if test="${item.status != 0}">
                <a href=${pageContext.request.contextPath}/order/delete?id=${item.id} onclick="return confirm('Are you sure?')">delete</a>
            </c:if>
        </td>

        </c:forEach>

    </tbody>
</table>

<%@include file="footer.jsp" %>

