<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="e"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@tag pageEncoding="UTF-8"%>

<!-- La sessió està disponible automàticament en l’objecte "session" -->
<c:set var="user" scope="request" value='${session.getAttribute("user")}'/>
<d:set var="role" scope="request" value='${user.role}' />
<e:set var="profile" scope="request" value='${session.getAttribute("profile")}'/>

<!-- <nav class="navbar navbar-default navbar-fixed-top"> -->

<div class="navbar-wrapper" id="nav">
<nav class="navbar navbar-custom">
	<div class="container">
	<!--<div class="container">-->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><span class="glyphicon glyphicon-home"></span>   Inicio</a> 
		</div>
		<div class="navbar-collapse collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<c:choose>
				<c:when test='${user != null}'>
					<d:choose>
					<d:when test='${role == 2}'>
						<li class="dropdown">
							<a href="#" data-toggle="dropdown" class="dropdown-toggle" aria-expanded="true">Actividades
							<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${pageContext.request.contextPath}/activity/list.html">Listar actividades</a></li>
								<li><a href="${pageContext.request.contextPath}/activity/customerList.html">Listar actividades (modo cliente)</a></li>
								<li><a href="${pageContext.request.contextPath}/activity/add.html">Añadir actividad</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" data-toggle="dropdown" class="dropdown-toggle" aria-expanded="true">Monitores 
							<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li class="dropdown-header">Monitores</li>
								<li><a href="${pageContext.request.contextPath}/instructor/list.html">Listar monitores</a></li>
								<li><a href="${pageContext.request.contextPath}/instructor/add.html">Añadir monitor</a></li>
								<li class="divider"></li>
								<li class="dropdown-header">Títulos</li>
								<li><a href="${pageContext.request.contextPath}/degree/list.html">Listar títulos</a></li>
								<li><a href="${pageContext.request.contextPath}/degree/add.html">Añadir título</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" data-toggle="dropdown" class="dropdown-toggle" aria-expanded="true">Clientes
							<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${pageContext.request.contextPath}/customer/list.html">Listar clientes</a></li>
								<li><a href="${pageContext.request.contextPath}/customer/add.html">Añadir cliente</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" data-toggle="dropdown" class="dropdown-toggle" aria-expanded="true">Reservas
							<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${pageContext.request.contextPath}/booking/list.html">Listar reservas</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" data-toggle="dropdown" class="dropdown-toggle" aria-expanded="true">Sugerencias
							<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${pageContext.request.contextPath}/suggestion/list.html">Listar sugerencias</a></li>
							</ul>
						</li>
					</d:when>
					<d:when test='${role==1}'>
						<li><a href="${pageContext.request.contextPath}/instructor/update/${profile.nif}.html">Editar perfil</a></li>
						<li><a href="${pageContext.request.contextPath}/instructor/changePwd/${user.username}.html">Cambiar contraseña</a></li>
						<li><a href="${pageContext.request.contextPath}/booking/instructorBookingList/${profile.nif}.html">Reservas asignadas</a></li>
					</d:when>
					<d:when test='${role==0}'>
						<li><a href="${pageContext.request.contextPath}/customer/update/${profile.nif}.html">Editar perfil</a></li>
						<li><a href="${pageContext.request.contextPath}/customer/changePwd/${user.username}.html">Cambiar contraseña</a></li>
						<li><a href="${pageContext.request.contextPath}/activity/customerList.html">Listar actividades</a></li>
						<li><a href="${pageContext.request.contextPath}/booking/customerBookingList/${profile.nif}.html">Mis reservas</a></li>
					</d:when>
					</d:choose>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/activity/customerList.html" id="nuestras-actividades">Nuestras actividades</a></li>
					<li></li>
				</c:otherwise>
				</c:choose>
			</ul>
			<t:logininfo />
		</div>
	<!--</div>-->
	</div>
</nav>
</div>