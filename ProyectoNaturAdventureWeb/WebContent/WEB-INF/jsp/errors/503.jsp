<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="NaturAdventure">
<jsp:body>
	
	<h1>Error 503</h1>
	<p>Servicio no disponible.</p>
	<p>Si desea comunicarlo para que nuestro departamento lo pueda corregir lo antes posible,
	puede hacerlo desde el siguiente enlace:</p>
	<p><a href="${pageContext.request.contextPath}/suggestion/add.html">Informar de un error</a></p>
	    
</jsp:body>
</t:paginabasica>