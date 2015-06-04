<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Perfil de usuario | NaturAdventure">
<jsp:body>
	
	<h2>Detalles del monitor</h2>
	    
	    <div class="left">
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">NIF: </span></div>
		    	<div class="col-sm-5">${instructor.nif}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre:</span></div>
		    	<div class="col-sm-5">${instructor.name}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Apellidos:</span></div>
		    	<div class="col-sm-5">${instructor.firstSurname} ${customer.secondSurname}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Email:</span></div>
		    	<div class="col-sm-5">${instructor.email}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Teléfono:</span></div>
		    	<div class="col-sm-5">${instructor.telephone}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Dirección:</span></div>
		    	<div class="col-sm-5">${instructor.address}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Fecha de cumpleaños:</span></div>
		    	<div class="col-sm-5">${instructor.dateOfBirthString}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Cuenta bancaria:</span></div>
		    	<div class="col-sm-5">${instructor.bankAccount}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre de usuario:</span></div>
		    	<div class="col-sm-5">${instructor.username}</div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/instructor/update/${instructor.nif}.html"><span class="glyphicon glyphicon-pencil"></span>  Editar datos</a></div>
		    	<div class="clear"></div>
		    </div>
		     <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/instructor/changePwd/${instructor.username}.html"><span class="glyphicon glyphicon-cog"></span>  Cambiar contraseña</a></div>
		    	<div class="clear"></div>
		    </div>
		    <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/instructor/delete/${instructor.nif}.html" onclick="return confirm('¿Estás seguro de que quieres borrar el monitor?');"><span class="glyphicon glyphicon-trash"></span>  Eliminar monitor</a></div>
		    	<div class="clear"></div>
		    </div>
		</div>
	    
</jsp:body>
</t:paginabasica>
