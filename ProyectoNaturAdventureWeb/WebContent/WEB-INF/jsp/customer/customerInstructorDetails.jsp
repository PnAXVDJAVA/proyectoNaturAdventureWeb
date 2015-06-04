<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Perfil de usuario | NaturAdventure">
<jsp:body>
	
	<h2>Detalles del cliente</h2>
	    
	    <div class="left">
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">NIF: </span></div>
		    	<div class="col-sm-5">${customer.nif}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre:</span></div>
		    	<div class="col-sm-5">${customer.name}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Apellidos:</span></div>
		    	<div class="col-sm-5">${customer.firstSurname} ${customer.secondSurname}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Email:</span></div>
		    	<div class="col-sm-5">${customer.email}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Teléfono:</span></div>
		    	<div class="col-sm-5">${customer.telephone}</div>
		    	<div class="clear"></div>
		    </div>
		</div>
		
	    
</jsp:body>
</t:paginabasica>
