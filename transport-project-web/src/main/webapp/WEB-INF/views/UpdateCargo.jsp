<%@include file="header.jsp" %>

<h1>Update cargo page</h1><br>

<form action="/cargo/UpdateCargoServletConfirmed" method="get">

    <input type="hidden" name="id" value="${cargo.id}"/>

    Name:<br>
    <input type="text" name="name" value="${cargo.name}" required placeholder="enter name" maxlength="20"
           pattern="[a-zA-Z\u0400-\u04ff]{3,20}"/><br><br>

    Weight, kg:<br>
    <input type="number" name="weight" value="${cargo.weight}" required placeholder="enter weight, kg" min="1"
           max="100000" maxlength="10"/><br><br>

    Status:<br>
    <select name="status" required>
        <option disabled selected value> -- select a status --</option>
        <option value="0" <c:if test="${cargo.status == 0}"><c:out value="selected"/></c:if>>ready</option>
        <option value="1" <c:if test="${cargo.status == 1}"><c:out value="selected"/></c:if>>shipped</option>
        <option value="2" <c:if test="${cargo.status == 2}"><c:out value="selected"/></c:if>>delivered</option>
    </select><br><br>

    <input type="submit" name="update" value="Submit"/>
    <input type="button" name="Cancel" value="Back" onclick="location.href='/cargo/getAll'"/>
</form>


<%@include file="footer.jsp" %>
