<%@include file="header.jsp" %>

<h1>Add driver page</h1><br>

<form action="${pageContext.request.contextPath}/driver/AddNewDriverServlet" method="get">
    <%--<fieldset>--%>
        <input type="hidden" name="id" value="${driver.id}"/>

        First name:<br>
        <input type="text" name="firstName" required placeholder="enter first name"
               value="${driver.firstName}"/><br><br>

        Last name:<br>
        <input type="text" name="lastName" required placeholder="enter last name" value="${driver.lastName}"/><br><br>

        Worked hours:<br>
        <input type="number" name="workedHours" required placeholder="enter worked hours"
               value="${driver.workedHours}"/><br><br>

        Status:<br>
        <%--<input type="text" name="status" required placeholder="enter status" value="${driver.status}"/><br><br>--%>
        <select name="status" required title="status">
            <option disabled selected value> -- select a status --</option>
            <option value="0" <c:if test="${driver.status == 0}"><c:out value="selected"/></c:if>> rest</option>
            <option value="1" <c:if test="${driver.status == 1}"><c:out value="selected"/></c:if>> onduty</option>
            <option value="2" <c:if test="${driver.status == 2}"><c:out value="selected"/></c:if>> drive</option>
        </select><br><br>

        CurrentCity:<br>
        <select name="city" required title="current city">
            <option disabled selected value> -- select a city--</option>
            <%--@elvariable id="cityAll" type="java.util.List"--%>
            <c:forEach var="item" items="${cityAll}">
                <option value="${item.id}"
                        <c:if test="${item.name == driver.currentCity.name}">
                            <c:out value="selected"/>
                        </c:if>>
                    <c:out value="${item.name}"/>
                </option>
            </c:forEach>
        </select><br><br>

        <%--CurrentTruck:<br>--%>
        <%--<select name="currentTruck">--%>
        <%--<option disabled selected value> -- select a truck -- </option>--%>
        <%--<c:forEach var="item" items="${truckAll}">--%>
        <%--<option value="${item.id}"--%>
        <%--&lt;%&ndash;<c:if test="${item.regNumber==order.currentTruck.regNumber}">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<c:out value="selected"/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
        <%-->--%>
        <%--<c:out value="${item.regNumber}"/>--%>
        <%--</option>--%>
        <%--</c:forEach>--%>
        <%--</select><br><br>--%>

        <input type="submit" name="update" value="Submit"/>
        <input type="button" name="Cancel" value="Back" onclick="location.href='/driver/getAll'"/>
    <%--</fieldset>--%>
</form>

<%@include file="footer.jsp" %>
