<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="profile" scope="request" value='${session.getAttribute("profile")}'/>

<t:paginabasica title="Nueva reserva | NaturAdventure">
<jsp:body>

		<h2>Reserva realizada</h2>
	    
	    <p>¡Enhorabuena, su solicitud de reserva se ha realizado con éxito! </p>
	    <p> En breves momentos recibirás un correo electrónico con los datos de la solicitud.<br>
	    Nuestro departamento pasará a comprobarla y en los próximos días recibirá, un nuevo correo electronico, con la comprobación de la misma</p>
	   	<p>¡Gracias por confiar en nosotros!</p>
	   	<p><a href="${pageContext.request.contextPath}/booking/customerBookingList/${profile.nif}.html">Ver lista de reservas</a></p>
	    
</jsp:body>
</t:paginabasica>
