<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset = "utf-8">
    <title>login Page</title>
    <link href="<c:url value="/resources/reset.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/structure.css"/>" rel="stylesheet">
</head>
<body>

<form:form method="post" commandName="user" action="/check-user" class="box login">

    <fieldset class="boxBody">

        <form:label path="name">Name/Driver id:</form:label>
        <form:input path="name" tabindex="1"/>

        <form:label path="password">Password:</form:label>
        <form:password path="password" tabindex="2"/>

        <footer>
            <label>
                <%--<input type="checkbox" id="checkbox" tabindex="3">Keep me logged in--%>
                <form:checkbox path = "driver" tabindex="3"/>I am a driver
            </label>
            <input type="submit" tabindex="4" class="btnLogin" value="Login"/>
        </footer>

    </fieldset>
</form:form>

    <%--Name:<input type="text" name="name"><br>--%>
    <%--Password:<input type="password" name="password"><br>--%>
    <%--<input type="submit" value="login">--%>

</body>