<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de monitores">
<jsp:body>
	
	<h2>Editar monitor</h2>
	    <form:form method="post" modelAttribute="instructor" role="form" class="form form-horizontal">
	    	<div class="form-group">
	                <form:label path="nif" class="control-label col-sm-2">NIF: </form:label>
	                <div class="col-xs-3">
	                	<form:input path="nif" readonly="true" class="form-control"/>
	                </div>
	                <form:errors path="nif" cssClass="error" />
	        </div>
	        <div class="form-group">
	                <form:label path="name" class="control-label col-sm-2">Nombre: </form:label>
	                <div class="col-xs-3">
	                	<form:input path="name" class="form-control"/>
	                </div>
	                <form:errors path="name" cssClass="error" />
	                
	        </div>
	        <div class="form-group">     
	                <form:label path="firstSurname" class="control-label col-sm-2">Primer apellido: </form:label>
	                <div class="col-xs-3">
	                	<form:input path="firstSurname" class="form-control"/>
	                </div>
	        </div>
	        <div class="form-group">
	                <form:label path="secondSurname" class="control-label col-sm-2">Segundo apellido: </form:label>
	                <div class="col-xs-3">
	                	<form:input path="secondSurname" class="form-control" />
	                </div>
	        </div>
	        <div class="form-group">
	                <form:label path="address" class="control-label col-sm-2">Dirección: </form:label>
	                <div class="col-xs-3">
	                	<form:input path="address" class="form-control"/>
	        		</div>
	        </div>
	        <div class="form-group">
	                <form:label path="telephone" class="control-label col-sm-2">Teléfono: </form:label>
	                <div class="col-xs-3">
	                	<form:input path="telephone" class="form-control"/>
	                </div>
	                <form:errors path="telephone" cssClass="error" />
	        </div>
	        <div class="form-group">
	                <form:label path="dateOfBirthString" class="control-label col-sm-2">Fecha de cumpleaños: ( DD/MM/AAAA ) </form:label>
	               	<div class="col-xs-3">
	                	<form:input path="dateOfBirthString" class="form-control"/>
	                </div>
	                <form:errors path="dateOfBirthString" cssClass="error" />
	        </div>
			<div class="form-group">
					<form:label path="email" class="control-label col-sm-2">E-mail: </form:label>
	                <div class="col-xs-3">
	                	<form:input path="email" class="form-control"/>
	                </div>
	                <form:errors path="email" cssClass="error" />
	        </div>
	        <div class="form-group">
	               	<form:label path="bankAccount" class="control-label col-sm-2">Cuenta bancaria: </form:label>
	                <div class="col-xs-3">
	                	<form:input path="bankAccount" class="form-control"/>
	                </div>
	                <form:errors path="bankAccount" cssClass="error" />
	        </div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
	                <button class="btn btn-default" type="submit">Confirmar cambios</button>
	                <button type="reset" class="btn btn-default">Limpiar</button>
	                <input type="button" class="btn btn-default" value="Cancelar" onclick="history.back(-1)"/>	                
	            </div>
	        </div>
	    </form:form>
	    
</jsp:body>
</t:paginabasica>
