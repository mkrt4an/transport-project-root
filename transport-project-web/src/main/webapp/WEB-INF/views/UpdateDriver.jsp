<%@include file="header.jsp" %>

<h1>Update diver page</h1><br>

<form action="UpdateDriverServletConfirmed" method="get">
    <input type="hidden" name="id" value="${driver.id}"/>

    First name:<br>
    <input type="text" name="firstName" required value="${driver.firstName}"/><br><br>

    Last name:<br>
    <input type="text" name="lastName" required value="${driver.lastName}"/><br><br>

    Worked hours:<br>
    <input type="number" name="workedHours" required value="${driver.workedHours}"/><br><br>

    Status:<br>
    <%--<input type="text" name="status" required value="${driver.status}"/><br><br>--%>
    <select name="status" required>
        <option disabled selected value> -- select a status -- </option>
        <option value="0" <c:if test="${driver.status == 0}"><c:out value="selected"/></c:if>> rest </option>
        <option value="1" <c:if test="${driver.status == 1}"><c:out value="selected"/></c:if>> onduty </option>
        <option value="2" <c:if test="${driver.status == 2}"><c:out value="selected"/></c:if>> drive </option>
    </select><br><br>

    CurrentCity:<br>
    <select name="city" required>
        <c:forEach var="item" items="${cityAll}">
            <option value="${item.id}"
                    <c:if test="${item.name==driver.currentCity.name}">
                        <c:out value="selected"/>
                    </c:if>>
                <c:out value="${item.name}"/>
            </option>
        </c:forEach>
    </select><br><br>

    <%--CurrentTruck:<br>--%>
    <%--<select name="currentTruck">--%>
        <%--&lt;%&ndash;<option disabled selected value> -- select an option -- </option>&ndash;%&gt;--%>
        <%--<c:forEach var="item" items="${truckAll}">--%>
            <%--<option value="${item.id}"--%>
                    <%--<c:if test="${item.regNumber==driver.currentTruck.regNumber}">--%>
                        <%--<c:out value="selected"/>--%>
                    <%--</c:if>>--%>
                <%--<c:out value="${item.regNumber}"/>--%>
            <%--</option>--%>
        <%--</c:forEach>--%>
    <%--</select><br><br>--%>

    <input type="submit" name="update" value="submit"/>
    <input type="button" name="Cancel" value="Back" onclick="location.href='/GetAllDriversServlet'"/>
</form>

<%@include file="footer.jsp" %>
