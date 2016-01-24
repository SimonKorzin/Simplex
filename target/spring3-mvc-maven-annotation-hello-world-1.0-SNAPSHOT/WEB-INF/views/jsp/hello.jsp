<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC + @JavaConfig</title>
 
<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>
 
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="/work">Spring 3 MVC Project @JavaConfig</a>
	</div>
  </div>
</nav>

<div class="container">
	<h2>Basic Table</h2>
	<p>The .table class adds basic styling (light padding and only horizontal dividers) to a table:</p>
	<table class="table">
		<thead>
		<tr>
			<%--<c:forEach var="listValue" items="${coofs}">--%>
				<%--<li><c:out value="${listValue}"/></li>--%>
			<%--</c:forEach>--%>
			<%--<c:forEach items="${coofs}" var="coof">--%>
				<%--<td>${coof.value}</td>--%>
			<%--</c:forEach>--%>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>John</td>
			<td>Doe</td>
			<td>john@example.com</td>
		</tr>
		<tr>
			<td>Mary</td>
			<td>Moe</td>
			<td>mary@example.com</td>
		</tr>
		<tr>
			<td>July</td>
			<td>Dooley</td>
			<td>july@example.com</td>
		</tr>
		</tbody>
	</table>
</div>


</div>
<div class="jumbotron">
  <div class="container">
	<h1>${title}</h1>
	<p>
		<c:if test="${not empty name}">
			Hello ${name}
		</c:if>

		<c:if test="${empty name}">
			Welcome Welcome!
		</c:if>
    </p>
    <p>
		<a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
	</p>
	</div>
</div>
 
<div class="container">
 
  <div class="row">
	<div class="col-md-4">
		<h2>Heading</h2>
		<p>ABC</p>
		<p>
			<a class="btn btn-default" href="#" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Heading</h2>
		<p>ABC</p>
		<p>
			<a class="btn btn-default" href="#" role="button">View details</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Heading</h2>
		<p>ABC</p>
		<p>
			<a class="btn btn-default" href="#" role="button">View details</a>
		</p>
	</div>
  </div>
 
 
  <hr>
  <footer>
	<p>&copy; Korzin.com 2015</p>
  </footer>
</div>
 
<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
 
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 
</body>
</html>