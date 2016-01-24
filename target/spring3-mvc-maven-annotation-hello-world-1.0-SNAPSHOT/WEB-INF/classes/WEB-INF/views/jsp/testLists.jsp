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
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Korzin BS-32</a>
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
        <c:forEach var="item" items="${row0}">
          <a class="btn btn-primary  " role="button">${item}</a>
        </c:forEach>
      </div>
      <div class="panel-body" >
        <c:forEach var="item" items="${row1}">
          <a class="btn btn-primary  " role="button">${item}</a>
        </c:forEach>
      </div>
      <table class="table table-hover">
        <thead>
          <c:forEach var="list" items="${lists}">
            <tr>
              <c:forEach var="item" items="${list}">
                <td>${item}</td>
              </c:forEach>
            </tr>
          </c:forEach>
        </thead>
      </table>
    </div>
  </div>
  </div>
</body>
</html>