<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de actividades">
<jsp:body>
		
		<h2>Lista de actividades</h2>
		
		<c:forEach items="${activities}" var="activity">
		
			<div class="activity-container" id="activity${activity.codActivity}">
				
				<div id="measuringWrapper${activity.codActivity}" class="msWrapper">
					<div class="activity-info-left">
						<h3 class="activityName">${activity.name}</h3>
						<p>Precio: ${activity.pricePerPerson} €</p>
					</div>
					
					<div class="menu-down-right">
						<a onClick="menuDown( ${activity.codActivity} )"><span class="glyphicon glyphicon-menu-down" id="downButton${activity.codActivity}"></span></a>
					</div>
					
					<div class="clear"></div>
					
					<div class="info-hidden"  id="${activity.codActivity}">
						<div class="button-book-activity">
							<button onClick="document.location.href='${pageContext.request.contextPath}/booking/book/${activity.codActivity}.html'"  class="btn btn-default">Reservar actividad</button>
						</div>
						<p>Descripción: ${activity.description}</p>
						<p>Nivel: ${activity.level}</p>
						<p>Duracion: ${activity.duration}</p>
						<p>Mínimo número de participantes: ${activity.minPartakers}</p>
						<p>Máximo número de participantes: ${activity.maxPartakers}</p>
					</div>
				</div>
			
			</div>
		
		</c:forEach>
		
		<script type="text/javascript">
		
			function menuDown( id ) {
				var activityDiv = document.getElementById( 'activity' + id );
				var wrapper = document.getElementById( 'measuringWrapper' + id );
				var down = 'glyphicon glyphicon-menu-down';
				var left = 'glyphicon glyphicon-menu-left'
				var downButtonClass = document.getElementById( 'downButton' + id ).className;
				if( downButtonClass == down ) {
					document.getElementById( id ).className = 'info-visible';
					activityDiv.style.height = wrapper.clientHeight + "px";
					document.getElementById( 'downButton' + id ).className = 'glyphicon glyphicon-menu-left';
				}
				else {
					activityDiv.style.height = "70px";
					document.getElementById( 'downButton' + id ).className = 'glyphicon glyphicon-menu-down';
					setTimeout( function() {
						document.getElementById( id ).className = 'info-hidden';
					}, 1000 );
				}
			}
		
		</script>
	
</jsp:body>
</t:paginabasica>


