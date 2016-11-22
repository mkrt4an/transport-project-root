<%@include file="header.jsp" %>

<%--<jsp:include page="../GlobalHeader.jsp">--%>
	<%--<jsp:param name="title" value="${errorCode}" />--%>
<%--</jsp:include>--%>

<div class="container">

	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Code: ${errorCode}</h3>
		</div>
	
		<ul class="list-group">
			<li class="list-group-item">${errorDiscription}</li>

			<c:if test="${!empty errorMsg}">
				<li class="list-group-item">${errorMsg}</li>
			</c:if>
		</ul>

		<div class="panel-footer">
			<a href="${pageContext.request.contextPath}">Home</a>
		</div>
	</div>
	
</div>

<%@include file="footer.jsp" %>