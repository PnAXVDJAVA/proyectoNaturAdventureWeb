<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<t:paginabasica title="Información de sugerencia | NaturAdventure">
<jsp:body>
	
	<h2>Detalles de la sugerencia</h2>
	    
	    <div class="left">
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Código: </span></div>
		    	<div class="col-sm-5">${suggestion.suggestion_code}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre: </span></div>
		    	<div class="col-sm-5">${suggestion.name}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">E-mail: </span></div>
		    	<div class="col-sm-5">${suggestion.email}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Tipo: </span></div>
		    	<div class="col-sm-5">${suggestion.messageType}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Mensaje: </span></div>
		    	<div class="col-sm-5 div-message-wrapper">
		    		<div class="div-message-wrapper-inside">${suggestion.message}</div>
		    	</div>
		    	<div class="clear"></div>
		    </div>
		    
		</div>
	    
</jsp:body>
</t:paginabasica>
