<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Editar monitor | NaturAdventure">
<jsp:body>
	
	<h2>Editar monitor</h2>
	    <form:form method="post" modelAttribute="instructor" role="form" class="form form-horizontal">
	    <div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="nif" class="control-label">NIF: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="nif" class="form-control" readonly="true"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="nif" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="name" class="control-label">Nombre: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="name" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="name" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="firstSurname" class="control-label">Primer apellido: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="firstSurname" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="firstSurname" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="secondSurname" class="control-label">Segundo apellido: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="secondSurname" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="secondSurname" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	            <div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="address" class="control-label">Dirección: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input path="address" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="address" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	            <div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="telephone" class="control-label">Teléfono: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-8">
		                	<form:input path="telephone" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-4">
	                	<form:errors path="telephone" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<label class="control-label">Fecha de cumpleaños: </label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-3">
		                	<form:input path="dayOfBirth" class="form-control" placeHolder="Día"/>
		           		</div>
		           		<div class="col-lg-3">
		                	<form:input path="monthOfBirth" class="form-control" placeHolder="Mes"/>
		           		</div>
		           		<div class="col-lg-4">
		                	<form:input path="yearOfBirth" class="form-control" placeHolder="Año"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="dayOfBirth" cssClass="error" />
	             	    <form:errors path="monthOfBirth" cssClass="error" />	                	
	                	<form:errors path="yearOfBirth" cssClass="error" />	                		             
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="email" class="control-label">E-mail: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input path="email" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="email" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-2">
	                	<form:label path="bankAccount" class="control-label">Cuenta bancaria: </form:label>
	                </div>
	                <div class="col-xs-4">
		                <div class="col-lg-10">
		                	<form:input path="bankAccount" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-4">
	                	<form:errors path="bankAccount" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	    	
			<div class="form-group">
				<div class="col-xs-2"></div>
				<div class="col-xs-5">
	                <button class="btn btn-custom btn-padding" type="submit">Confirmar cambios</button>
	                <button type="reset" class="btn btn-custom btn-padding">Limpiar</button>
	                <input type="button" class="btn btn-custom btn-padding" value="Cancelar" onclick="history.back(-1)"/>	                
	            </div>
	        </div>
	    </form:form>
	    
</jsp:body>
</t:paginabasica>
