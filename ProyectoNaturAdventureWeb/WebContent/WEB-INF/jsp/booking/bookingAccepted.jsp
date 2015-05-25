<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="profile" scope="request" value='${session.getAttribute("profile")}'/>

<t:paginabasica title="Gestión de reservas">
<jsp:body>

		<h2>Reserva realizada</h2>
	    
	    <p>¡Enhorabuena, tu reserva se ha realizado con éxito! En breve recibirás un correo electrónico con los datos de la reserva
	    	y más adelante otro con la confirmación o no de la realización de la actividad propuesta.</p>
	   	<p>¡Gracias por confiar en nosotros!</p>
	   	<p><a href="${pageContext.request.contextPath}/booking/customerBookingList/${profile.nif}.html">Ver lista de reservas</a></p>
	    
</jsp:body>
</t:paginabasica>
