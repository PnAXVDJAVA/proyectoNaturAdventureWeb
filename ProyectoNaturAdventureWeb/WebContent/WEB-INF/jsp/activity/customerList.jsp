<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de actividades">
<jsp:body>
		
		<h2>Lista de actividades</h2>
		
		<c:forEach items="${activities}" var="activity">
		
			<div class="container activity-container" id="activity${activity.codActivity}">
			
				<div class="activity-info-left">
					<h3>${activity.name}</h3>
					<p>Precio: ${activity.pricePerPerson} €</p>
				</div>
				
				<div class="menu-down-right">
					<a onClick="menuDown( ${activity.codActivity} )"><span class="glyphicon glyphicon-menu-down" id="downButton${activity.codActivity}"></span></a>
				</div>
				
				<div class="clear"></div>
				
				<div class="info-hidden"  id="${activity.codActivity}">
					<p>Descripción: ${activity.description}</p>
					<p>Nivel: ${activity.level}</p>
					<p>Duracion: ${activity.duration}</p>
					<p>Mínimo número de participantes: ${activity.minPartakers}</p>
					<p>Máximo número de participantes: ${activity.maxPartakers}</p>
					<a href="${pageContext.request.contextPath}/booking/book/${activity.codActivity}.html">Reservar actividad</a>
				</div>
			
			</div>
		
		</c:forEach>
		
		<script type="text/javascript">
		
			function menuDown( id ) {
				var down = 'glyphicon glyphicon-menu-down';
				var left = 'glyphicon glyphicon-menu-left'
				var downButtonClass = document.getElementById( 'downButton' + id ).className;
				if(  downButtonClass == down ) {					
					document.getElementById( id ).className = 'info-visible';
					document.getElementById( 'downButton' + id ).className = 'glyphicon glyphicon-menu-left';
				}
				else {
					document.getElementById( id ).className = 'info-hidden';
					document.getElementById( 'downButton' + id ).className = 'glyphicon glyphicon-menu-down';
				}
			}
		
		</script>
	
</jsp:body>
</t:paginabasica>


