<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="result" scope="request" value='${recoveryResult}'/>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de clientes">
<jsp:body>
	
	<h2>Recuperación de contraseña</h2>
	<c:choose>
		<c:when test="${result == true }">
			<p>Hemos verificado el email y el nombre de usuario y son correctos. En breve te enviaremos
				un correo electrónico con la nueva contraseña.</p>
		</c:when>
		<c:otherwise>
			<p>Lo sentimos, el nombre de usuario y el email introducidos no son correctos.</p>
		</c:otherwise>
	</c:choose>
	    
</jsp:body>
</t:paginabasica>
