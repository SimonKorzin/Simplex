<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Korzin BS-32 Linear Regression + LinProg</title>

  <spring:url value="/resources/core/css/hello.css" var="coreCss" />
  <spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
  <link href="${bootstrapCss}" rel="stylesheet" />
  <link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Korzin BS-32 Linear Regression + LinProg</a>
    </div>
  </div>
</nav>
<nav class="navbar navbar-inverse">
  <div class="container">
  </div>
</nav>
<div>

    <div class="row">
    </div>


        <div class="col-sm-4">
          <h3>All variables</h3>
          <div class="panel panel-default">
            <div class="panel-body" >
        <c:forEach var="name" items="${contr}">
          <a class="btn btn-primary  " href="/work/${name}/add" role="button">${name}</a>
        </c:forEach>
        </div>
      </div>
    </div>
  <div class="col-sm-1">
   <h3>Choose</h3>
        <h1><span class="glyphicon glyphicon-resize-horizontal" /></h1>
        <h1><span class="glyphicon glyphicon-resize-horizontal"/> </h1>
        </div></div></div>
    <div class="col-sm-4">
      <h3>Control variables</h3>
      <div class="panel panel-default">
        <div class="panel-body">
          <c:forEach var="name" items="${choose}">
             <a class="btn btn-warning  " href="/work/${name}/revert" role="button">${name}</a>
          </c:forEach>
        </div>
  </div>
    </div>
  </div>

<div class="row">
</div>


<div class="col-sm-4">
  <h3>All variables</h3>
  <div class="panel panel-default">
    <div class="panel-body" >
      <c:forEach var="name" items="${contr}">
          <a class="btn btn-primary  " href="/work/${name}/dependent" role="button">${name}</a>
      </c:forEach>
    </div>
  </div>
</div>
<div class="col-sm-1">
  <h3>Choose one</h3>
  <h1><span class="glyphicon glyphicon-resize-horizontal" /></h1>
</div></div></div>
<div class="col-sm-4">
  <h3>Dependent variable</h3>
  <div class="panel panel-default">
    <div class="panel-body">
      <c:if test="${not empty relative}">
        <a class="btn btn-danger  " href="/work/${relative}/revert" role="button">${relative}</a>
      </c:if>
    </div>
  </div>
</div>
<div class="row" >
    <div class="col-sm-6">
        <a href="/work/execution" class="btn btn-success btn-block" role="button">Calculate linear regression <span class="glyphicon glyphicon-equalizer"/></a>
    </div>
</div>



<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>