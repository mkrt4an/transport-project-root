<%@include file="header.jsp" %>

<h1>Add order page</h1><br>

<form name="NewCity" action="/new-city">

    <%--Passing params--%>
    <input hidden name="routePointId" value="${routePointId}"/>
    <input hidden name="orderId" value="${orderId}"/>
    <%--<input hidden name="cityId" value="${cityId}"/>--%>

        <div id="city">
            City:<br>
            <select id="cityListMain" name="cityId" required placeholder="enter city">
                <option disabled selected value> -- select a city --</option>
                <c:forEach var="item" items="${cityAll}">
                    <option value="${item.id}"
                            <c:if test="${item.id==routePoint.city.id}">
                                <c:out value="selected"/>
                            </c:if>>
                        <c:out value="${item.name}"/>
                    </option>
                </c:forEach>
            </select>
            <%--<input id="citySubmit" type="submit" value="ADD" name=""/>--%>
        </div>

        <hr>

        <div id="main">
        <div id="load">
            <%--<form name="NewLoad" action="/new-cargo">--%>
            New load:<br><br>

            name: <br>
                <input id="cargoName" type="text" required placeholder="enter name" name="cargoName" /><br/><br>

            weight: <br>
                <input id="cargoWeight" type="number" required placeholder="enter weight, kg" name="cargoWeight" value=""/><br/><br>

            <%--<input hidden name="routePointId" value="${routePointId}"/>--%>
            <%--<input hidden name="orderId" value="${orderId}"/>--%>

            <input id="cargoSubmit" type="button" value="Add cargo" name=""/>
            <%--</form>--%>
        </div>


        <div id="added">
            Added cargo:<br><br>
            <table id="addCargoTable" border="1px">
                <tr>
                    <th>name</th>
                    <th>weight</th>
                </tr>
                <%--<c:forEach var="item" items="${cargoToLoadList}">--%>
                    <%--<tr>--%>
                        <%--<td><c:out value="${item.name}"/></td>--%>
                        <%--<td><c:out value="${item.weight}"/></td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
            </table>
        </div>

    </div>

    <hr>

    <div id="delivery">
        Delivery cargo:<br><br>
        <table id="cargoDeliveryList" border=1px>
            <tr>
                <th>Check</th>
                <th>name</th>
                <th>weight</th>
            </tr>
            <%--<c:forEach var="item" items="${cargoToDeliverList}">--%>
                <%--<tr>--%>
                    <%--<td><input type="checkbox"></td>--%>
                    <%--<td><c:out value="${item.name}"/></td>--%>
                    <%--<td><c:out value="${item.weight}"/></td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
        </table>
    </div>

    <hr>

    <div>
        <%--<form name="NewRoutePoint" action="/route-point">--%>
        <%--<input hidden name="routePointId" value="${routePointId}"/>--%>
        <%--<input hidden name="cityId" value="${cityId}"/>--%>
        <%--<input hidden name="orderId" value="${orderId}"/>--%>
        <input id="nextRoutePoint" type="button" value="Next route point" name="ADD RP"/>
        <%--</form>--%>

        <%--<form name="ready" action="/ready">--%>
        <%--<input hidden name="routePointId" value="${routePointId}"/>--%>
        <%--<input hidden name="cityId" value="${cityId}"/>--%>
        <%--<input hidden name="orderId" value="${orderId}"/>--%>
        <input id="saveOrder" type="button" value="Save order" name="READY"/>
        <%--</form>--%>
    </div>

</form>

<%@include file="footer.jsp" %>