<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<d:set var="picture" scope="request" value='${activity.pictureString}'/>
<t:paginabasica title="Información de título | NaturAdventure">
<jsp:body>
	
	<h2>Detalles del título</h2>
	    
	    <div class="left">
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Código: </span></div>
		    	<div class="col-sm-5">${degree.codDegree}</div>
		    	<div class="clear"></div>
		    </div>
		    
		   <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre:</span></div>
		    	<div class="col-sm-5">${degree.name}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Descripción:</span></div>
		    	<div class="col-sm-5">${degree.description}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/degree/update/${degree.codDegree}.html"><span class="glyphicon glyphicon-pencil"></span>  Editar datos</a></div>
		    	<div class="clear"></div>
		    </div>
		    
		     <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/degree/delete/${degree.codDegree}.html" onclick="return confirm('¿Estás seguro de que quieres borrar el título?');"><span class="glyphicon glyphicon-trash"></span>  Eliminar título</a></div>
		     	<div class="clear"></div>
		     </div>
		</div>
		
</jsp:body>
</t:paginabasica>
