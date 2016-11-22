</dev>

<hr/>
<footer>
    <p>&copy; 2016 Java school.</p>


    <c:forEach items="${sessionScope}" var="attr">
        ${attr.key}=${attr.value}<br><br>
    </c:forEach>

</footer>

</body>
</html>
