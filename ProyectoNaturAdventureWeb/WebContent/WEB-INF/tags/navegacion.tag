<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@tag pageEncoding="UTF-8"%>
<!-- La sessió està disponible automàticament en l’objecte "session" -->
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>
<d:set var="role" scope="request" value='${user.role}' />
<p class="loggeduser">
<c:choose>
<c:when test='${user != null}'>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<d:choose>
					<d:when test='${role == 2}'>
						<li><a href="${pageContext.request.contextPath}/index.jsp">Página principal</a></li>
						<li><a href="${pageContext.request.contextPath}/instructor/list.html">Gestión de monitores</a></li>
						<li><a href="${pageContext.request.contextPath}/activity/list.html">Gestión de actividades</a></li>
						<li><a href="${pageContext.request.contextPath}/degree/list.html">Gestión de títulos</a></li>
					</d:when>
					<d:otherwise>
						<li><a href="${pageContext.request.contextPath}/index.jsp">Página principal</a></li>
						<li><a href="${pageContext.request.contextPath}/index.jsp">Reservas asignadas</a></li>
					</d:otherwise>
					</d:choose>
				</ul>
			</div>
		</div>
	</nav>
</c:when>
</c:choose>
</p>