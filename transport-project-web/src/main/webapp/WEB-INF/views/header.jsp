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
        <span>${user.name}, <a href="/">Logout</a></span>
    </hgroup>
</header>

<nav>
    <ul class="main-menu">
        <li><a href="/driver/add">Add New Driver</a></li>
        <li><a href="/truck/add">Add New Truck</a></li>
        <li><a href="/cargo/add">Add New Cargo</a></li>
        <li><a href="/order/add">Add New Order</a></li>


        <li><a href="/driver/getAll">Get all Drivers</a></li>
        <li><a href="/truck/getAll">Get all Trucks</a></li>
        <li><a href="/cargo/getAll">Get all Cargoes</a></li>
        <li><a href="/order/getAll">Get all Orders</a></li>
    </ul>
</nav>

<div>