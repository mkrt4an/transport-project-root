<%@include file="header.jsp" %>

<h1>All drivers page</h1><br>

<table border=1 width=1 cellspacing=2 cellpadding=2>
    <thead>
    </thead>
    <tbody>
    <tr>
        <th>
            <c:out value="id"/>
        </th>
        <th>
            <c:out value="firstName"/>
        </th>
        <th>
            <c:out value="lastName"/>
        </th>
        <th>
            <c:out value="workedHours"/>
        </th>
        <%--<th>--%>
            <%--<c:out value="currentCity"/>--%>
        <%--</th>--%>
        <%--<th>--%>
        <%--<c:out value="currentTruck"/>--%>
        <%--</th>--%>
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
            <c:out value="${item.firstName}"/>
        </td>
        <td>
            <c:out value="${item.lastName}"/>
        </td>
        <td>
            <c:out value="${item.workedHours}"/>
        </td>
        <%--<td>--%>
            <%--<c:out value="${item.currentCity.name}"/>--%>
        <%--</td>--%>
            <%--<td>--%>
            <%--<c:out value="${item.currentTruck.regNumber}"/>--%>
            <%--</td>--%>
        <td>
                <%--<c:out value="${item.status}"/>--%>
            <c:if test="${item.status == 0}"><c:out value="rest"/></c:if>
            <c:if test="${item.status == 1}"><c:out value="onduty"/></c:if>
            <c:if test="${item.status == 2}"><c:out value="drive"/></c:if>
        </td>
        <td>
            <%--<c:if test="${item.order == null}">--%>
                <a href=${pageContext.request.contextPath}/driver/update?id=${item.id}>update</a>
            <%--</c:if>--%>
            <%--<c:if test="${item.order != null}">--%>
                <%-----%>
            <%--</c:if>--%>
        </td>
        <td>
            <%--<c:if test="${item.order == null}">--%>
                <a href=${pageContext.request.contextPath}/driver/delete?id=${item.id} onclick="return confirm('Are you sure?')">delete</a>
            <%--</c:if>--%>
            <%--<c:if test="${item.order != null}">--%>
                <%-----%>
            <%--</c:if>--%>
        </td>

        </c:forEach>

    </tbody>
</table>

<%@include file="footer.jsp" %>

