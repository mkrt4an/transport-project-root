<%@include file="header.jsp" %>

<%--<div id="city">--%>
    <%--City:&nbsp;&nbsp;&nbsp;--%>
    <%--<input id="cityList" list="browsers" name="browser">--%>
    <%--<datalist id="browsers">--%>
        <%--<option value="spb">--%>
        <%--<option value="msk">--%>
        <%--<option value="nyk">--%>
        <%--<option value="lnd">--%>
        <%--<option value="ism">--%>
    <%--</datalist>--%>
<%--</div>--%>


<div id="city">
    City:<br>
    <select id="cityList" name="cityId" required placeholder="enter city">
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

<div id="main2">
    <div id="main">
        <div id="load">
            New load
            <br>
            <br>
            Name:&nbsp;&nbsp;
            <input id="cargoName" type="text" required name="name"  title="name"/>
            <br>
            <br>
            Weight:
            <input id="cargoWeight" type="number" required name="weight"  title="weight"/>
            <br>
            <br>
            <input id="addCargo" type="submit" value="SAVE CARGO" />

        </div>
        <div id="added">
            added
            <br>
            <br>
            <table id="loadTable" border="1px">
                <thead>
                <tr>
                    <th>name</th>
                    <th>weight</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
</div>

<hr>

<div id="delivery">
    <div id="deliver-left">
        avilable for delivery
        <br>
        <br>
        <table id="deliverTable" border=1px>
            <thead>
            <tr>
                <th>check</th>
                <th>name</th>
                <th>weight</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>

        <!-- 		<table id="rpdl" border=1px>
                    <thead>
                        <tr>
                            <th>check</th>
                            <th>name</th>
                            <th>weight</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                        </table> -->
    </div>
    <%--<div id="deliver-right">--%>
        <%--deliver at this RP--%>
        <%--<br>--%>
        <%--<br>--%>
        <%--<table id="deliverTable2" border=1px>--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<!-- <th>check</th> -->--%>
                <%--<th>name</th>--%>
                <%--<th>weight</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody></tbody>--%>
        <%--</table>--%>
    <%--</div>--%>
</div>

<hr>

<div>
    <input id="addRp" type="submit" value="Save routpoint" name="ADD RP" />

    <form action="${pageContext.request.contextPath}/order/add/test">
        <input id="ready" type="submit" value="Save order" name="READY" />
        <hr>
        <input id="orderText" type="text" value="SAVE ORDER" name="order" hidden title="save order"/>
    </form>

    <%--<input id="test" type="button" value="test" name="test" />--%>
    <%--<input id="testck" class="ck2" type="checkbox" value="testck" name="testck" />--%>
    <%--<input id="testck2" class="ck2" type="checkbox" value="testck" name="testck" />--%>
    <%--<input id="testck3" class="ck2" type="checkbox" value="testck" name="testck" />--%>

    <!-- <input id="testInput" type="text" value="text" name="text" /> -->
</div>

<div id="testDiv"></div>
    
    
    

    
    
 


