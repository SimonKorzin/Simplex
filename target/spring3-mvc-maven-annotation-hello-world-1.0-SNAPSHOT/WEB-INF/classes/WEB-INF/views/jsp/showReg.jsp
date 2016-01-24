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
      <a class="navbar-brand" href="#">Korzin BS-32 Linear Regression + LinProg</a>
    </div>
  </div>
</nav>
<nav class="navbar navbar-inverse">
  <div class="container">
  </div>
</nav>


  <div class="row">
    <h3>SSelected variables</h3>
  </div>


  <div class="col-sm-8">



      <table class="table table-hover">
        <thead>
          <tr>
              <c:forEach var="name" items="${choose}">
                <c:choose>
                  <c:when test="${name == relative}">
                    <%--<c:if test="${checkRelative == true}">--%>
                      <td><a class="btn btn-danger  " role="button">${name}</a></td>
                      <%--<c:set var="checkRelative" value="${false}"></c:set>--%>
                    <%--</c:if>--%>
                  </c:when>
                  <c:otherwise>
                    <td><a class="btn btn-warning " role="button">${name}</a></td>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
          </tr>
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
  <div class="col-sm-3">
    <div class="panel panel-info">
      <div class="panel-heading">Sum Of Squares</div>
      <div class="panel-body">${SumOfSquares}</div>
    </div>
    <div class="panel panel-info">
      <div class="panel-heading">RSquare</div>
      <div class="panel-body">${RSquare}</div>
    </div>
    <div class="panel panel-info">
      <div class="panel-heading">Adjusted RSquared</div>
      <div class="panel-body">${AdjustedRSquared}</div>
    </div>
    <div class="panel panel-info">
      <div class="panel-heading">Standart Error</div>
      <div class="panel-body">${StdError}</div>
    </div>
  </div>
  <div class="col-sm-3">
    <div class="panel panel-warning">
      <div class="panel-heading">Coefficients</div>
      <div class="panel-body">
        <c:forEach  var="coof" items="${coefficients}">
            <p>${coof}</p>
        </c:forEach>
    </div>
  </div>

</div>
<div class="col-sm-3">
  <a href="/linprog" class="btn btn-success form-control" role="button">Use LinProg!</a>
  </div>
</body>
</html>