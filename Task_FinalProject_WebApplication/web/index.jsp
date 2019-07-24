<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <c:url value="/index.html" var="indexActionURL"/>
  <c:url value="/login.html" var="loginActionURL"/>
  <c:url value="/register.html" var="registerActionURL"/>
  <c:url value="/logout.html" var="logoutActionURL"/>
  <c:url value="/user/profile.html" var="profileActionURL"/>
  <fmt:setLocale value="${sessionLang}"/>
  <fmt:setBundle basename="by.training.finaltask.resource.localization"/>
  <title><fmt:message key="title"/></title>
  <!-- Bootstrap core CSS -->
  <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<script src="js/popper.min.js"></script>
<!-- MenuItem -->
<jsp:include page="/jsp/tags/menu.jsp" flush="true"/>

<form action="${indexActionURL}" method="post" >
  <select name="lang" class="custom-select-sm float-right" >
    <option value ="${sessionLang}" ><fmt:message key="pickLanguage"/></option>
    <option value="en_US"><fmt:message key="english"/></option>
    <option value="ru_RU"><fmt:message key="russian"/></option>
    <option value="de_DE"><fmt:message key="german"/></option>
  </select>
  <button class="btn float-right" type="submit"><fmt:message key="changeLanguage"/></button>
</form>

<br>
<c:if test="${not empty message}">
  <center>
    <label class="text"><fmt:message key="${message}"/></label>
  </center>
</c:if>
<!-- Page Content -->
<center>
  <img src="img/title1.jpg" height="600" width="1000" class="img-fluid" alt="Title Image">
</center>

<div class="container">
  <div class="row">
    <div class="col-lg-12 text-center">
      <h2>
        <fmt:message key="whyAdopt"/>
      </h2>
      <p class="lead"><fmt:message key="didyouknow"/></p>

    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="/js/bootstrap.bundle.min.js"></script>

<jsp:include page="/jsp/tags/footer.jsp" flush="true"/>
</body>

</html>

