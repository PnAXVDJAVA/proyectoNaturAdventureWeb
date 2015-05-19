<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- La sessió està disponible automàticament en l’objecte "session" -->
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>
<c:choose>
<c:when test='${user == null}'>
<ul class="nav navbar-nav navbar-right">
	<li><a class="no-padding" href="${pageContext.request.contextPath}/customer/add.html"><span class="glyphicon glyphicon-user"></span>   Registrarse</a></li>
	<li><a class="no-padding" href="${pageContext.request.contextPath}/login.html"><span class="glyphicon glyphicon-log-in"></span>   Login</a></li>
</ul>
</c:when>
<c:otherwise>
<ul class="nav navbar-nav navbar-right">
	<li class="auth">Autenticado como:  <span class="username">${user.username}</span></li>
	<li><a class="no-padding" href="${pageContext.request.contextPath}/logout.html"><span class="glyphicon glyphicon-log-out"></span>   Salir</a></li>
</ul>
</c:otherwise>
</c:choose>