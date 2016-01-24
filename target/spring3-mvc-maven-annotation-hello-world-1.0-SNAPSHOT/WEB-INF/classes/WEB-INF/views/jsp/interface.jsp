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
<form  role="role" action="#" method="post">
  <div class="col-sm-12">
    <div class="panel panel-warning">
        <div class="panel-heading">
      <div class="alert alert-warning" role="alert">
          <div class="form-group">
            <c:forEach var="item" items="${coofs}" varStatus="loop">
                <div class="col-xs-2"><input type="text" class="form-control input-sm" id="x${loop.index+1}" name="x${loop.index+1}"  value="${item}"></div>
                <div class="col-xs-1"><c:choose>
                                            <c:when test="${loop.index == 6}">
                                              <a>      </a>
                                            </c:when>
                                            <c:otherwise>
                                              <a>x${loop.index+1} <span class="glyphicon glyphicon-plus warning"/></a>
                                            </c:otherwise>

                                      </c:choose>
                </div>
            </c:forEach>
          </div>
      </div>
            </div>
    </div>
  </div>
</form>
<form  role="role" action="/linprog" method="post">
  <div class="row">
  <div class="col-sm-3">
    <div class="panel panel-default">
      <div class="panel-body">
        <div class="form-group">
         <input type="text" class="form-control input-sm" id="x1stl" name="x1stl"  value="121.4">
         <input type="text" class="form-control input-sm" id="x2stl" name="x2stl"  value="133">
         <input type="text" class="form-control input-sm" id="x3stl" name="x3stl"  value="20">
         <input type="text" class="form-control input-sm" id="x4stl" name="x4stl"  value="1400">
         <input type="text" class="form-control input-sm" id="x5stl" name="x5stl"  value="83.4">
         <input type="text" class="form-control input-sm" id="x6stl" name="x6stl"  value="4.5">
        </div>
      </div>
    </div>
  </div>
  <div class="col-sm-1">
     <div class="panel panel-info">
      <div class="panel-heading">
        <p></p>
        <p><span class="glyphicon glyphicon-menu-left"/>x1<span class="glyphicon glyphicon-menu-left"/></p>
        <p><span class="glyphicon glyphicon-menu-left"/>x2<span class="glyphicon glyphicon-menu-left"/></p>
        <p><span class="glyphicon glyphicon-menu-left"/>x3<span class="glyphicon glyphicon-menu-left"/></p>
        <p><span class="glyphicon glyphicon-menu-left"/>x4<span class="glyphicon glyphicon-menu-left"/></p>
        <p><span class="glyphicon glyphicon-menu-left"/>x5<span class="glyphicon glyphicon-menu-left"/></p>
        <p><span class="glyphicon glyphicon-menu-left"/>x6<span class="glyphicon glyphicon-menu-left"/></p>

      </div>
    </div>
  </div>
    <div class="col-sm-3">
      <div class="panel panel-default">
        <div class="panel-body">
          <div class="form-group">
            <input type="text" class="form-control input-sm" id="x1str" name="x1str"  value="175.6">
            <input type="text" class="form-control input-sm" id="x2str" name="x2str"  value="200">
            <input type="text" class="form-control input-sm" id="x3str" name="x3str"  value="40">
            <input type="text" class="form-control input-sm" id="x4str" name="x4str"  value="2300">
            <input type="text" class="form-control input-sm" id="x5str" name="x5str"  value="99.6">
            <input type="text" class="form-control input-sm" id="x6str" name="x6str"  value="6.5">
          </div>
        </div>
      </div>
    </div>
      <div class="col-sm-3">
          <div class="panel panel-info">
              <div class="panel-heading">Result</div>
              <div class="panel-body">
      <c:forEach var="item" items="${result}" varStatus="loop">
      <p>X[${loop.index+1}] = ${item}</p>
    </c:forEach>
              </div></div></div>

    <div class="col-sm-2">
        <div class="panel panel-info">
            <div class="panel-heading">Criteria</div>
            <div class="panel-body">
                <p>${criteria*0.75}</p>
            </div>
        </div>
    </div>
      </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-12">
      <button type="submit" class="btn btn-success">Calculate</button>
    </div>
  </div>
</form>


</body>
</html>
