<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<t:paginabasica title="Aprobar reserva - Resultado | NaturAdventure">
<jsp:body>

	<h2>Aprobación de la reserva</h2>
	
	<c:choose>
		<c:when test="${acceptResult == true}">
			<p>La reserva se ha aceptado con éxito. Se ha enviado un correo electrónico al cliente correspondiente 
				para indicarle que la reserva ha sido aceptada así como un correo a los monitores asignados a la
				reserva.</p>
		</c:when>
		<c:otherwise>
			<p>No se ha podido aceptar la reserva porque es necesario asignar primero a un monitor. Puedes hacerlo 
				en la parte derecha de la pantalla, desde <a href="${pageContext.request.contextPath}/booking/bookingDetails/${codBooking}.html">aquí</a>.</p>
		</c:otherwise>	
	</c:choose>

</jsp:body>
</t:paginabasica>