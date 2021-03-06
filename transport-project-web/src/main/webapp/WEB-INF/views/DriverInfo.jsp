<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>TransportProject</title>
    <link href="<c:url value="/resources/styles.css" />" rel="stylesheet">
    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
    <script src="http://code.jquery.com/jquery-3.1.1.js"   integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA=" crossorigin="anonymous"></script>
    <script src="<c:url value="/resources/jScript.js" />"></script>
</head>
<body>

<header class="mainH">
    <hgroup>
        <span><h1>TransportProject Application</h1></span>
        <span>${driver.firstName} ${driver.lastName}, <a href="/">Logout</a></span>
    </hgroup>
</header>

<h1>Driver info page</h1><br>
<hr>
<h1>Order id: ${order.id}</h1>
<br>
<hr>
Driver list:
<br>
<c:forEach var="item" items="${order.driverList}">
    <c:out value="${item.firstName}"/>
    <c:out value="${item.lastName}"/>
    <c:out value="id: ${item.id}"/>
    <br/>
</c:forEach>

<hr>
Truck regNumber: ${order.currentTruck.regNumber}
<br>

<hr>
Route points:
<br>
    <c:forEach var="item" items="${order.routePointList}">
        <c:out value = "${item.ordinal} - ${item.city.name}"/><br/>
    </c:forEach>
<hr>



<%@include file="footer.jsp" %>
