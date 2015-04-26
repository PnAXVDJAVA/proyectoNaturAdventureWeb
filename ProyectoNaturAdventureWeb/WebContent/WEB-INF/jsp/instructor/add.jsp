<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de monitores">
<jsp:body>
	
	<h2>Nuevo monitor</h2>
	    <form:form method="post" modelAttribute="instructorUser" role="form" class="form form-horizontal">
	        	<div class="form-group">
	       			<div class="col-md-3">
			         	<form:label path="instructor.nif" class="control-label" >NIF: </form:label>
			            <form:input path="instructor.nif" class="form-control" />
			            <form:errors path="instructor.nif" cssClass="error" />
			        </div>
			    </div>
	            <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="instructor.name">Nombre: </form:label>
	                	<form:input path="instructor.name" class="form-control"/>
	                	<form:errors path="instructor.name" cssClass="error" />
	            	</div>
	            </div>
	            <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="instructor.firstSurname">Primer apellido: </form:label>
	                	<form:input path="instructor.firstSurname" class="form-control" />
	            	</div>
	            </div>
	             <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="instructor.secondSurname">Segundo apellido: </form:label>
	                	<form:input path="instructor.secondSurname" class="form-control"/>
	            	</div>
	            </div>
	            <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="instructor.address">Dirección: </form:label>
	                	<form:input path="instructor.address" class="form-control"/>
	            	</div>
	            </div>
	            <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="instructor.telephone">Teléfono: </form:label>
	                	<form:input path="instructor.telephone" class="form-control"/>
	                	<form:errors path="instructor.telephone" cssClass="error" />
	            	</div>
	            </div>
	            <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="instructor.dateOfBirthString">Fecha de cumpleaños: ( DD/MM/AAAA ) </form:label>
	                	<form:input path="instructor.dateOfBirthString" class="form-control"/>
	                	<form:errors path="instructor.dateOfBirthString" cssClass="error" />
	            </div>
	            </div>
	            <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="instructor.email">E-mail: </form:label>
	                	<form:input path="instructor.email" class="form-control"/>
	                	<form:errors path="instructor.email" cssClass="error" />
	            	</div>
	            </div>
	            <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="instructor.bankAccount">Cuenta bancaria: </form:label>
	                	<form:input path="instructor.bankAccount" class="form-control"/>
	                	<form:errors path="instructor.bankAccount" cssClass="error" />
	            	</div>
	             </div>
	             <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="userDetails.username">Username: </form:label>
	                	<form:input path="userDetails.username" class="form-control"/>
	                	<form:errors path="userDetails.username" cssClass="error" />
	            	</div>
	            </div>
	            <div class="form-group">
	       			<div class="col-md-3">
	                	<form:label path="userDetails.password">Contraseña: </form:label>
	                	<form:input type="password" path="userDetails.password" class="form-control"/>
	                	<form:errors path="userDetails.password" cssClass="error" />
	            	</div>
	            </div>	            
	            <button type="submit" class="btn btn-default">Añadir monitor</button>
	    </form:form>
	    
</jsp:body>
</t:paginabasica>
