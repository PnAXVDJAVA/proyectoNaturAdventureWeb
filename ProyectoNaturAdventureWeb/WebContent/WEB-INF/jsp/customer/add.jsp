<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Gestión de clientes">
<jsp:body>
	
	<h2>Registro de usuario</h2>
	    <form:form method="post" modelAttribute="customerUser" role="form" class="form form-horizontal">
	        	<div class="form-group">
			         	<form:label path="customer.nif" class="control-label col-sm-2" for="nif" >NIF: </form:label>
			         	<div class="col-xs-3">
			            	<form:input path="customer.nif" class="form-control" id="nif" />
			            </div>
			            <form:errors path="customer.nif" cssClass="error" />
			    </div>
	            <div class="form-group">
	                	<form:label path="customer.name" class="control-label col-sm-2">Nombre: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="customer.name" class="form-control"/>
	                	</div>
	                	<form:errors path="customer.name" cssClass="error" />
	            </div>
	            <div class="form-group">
	                	<form:label path="customer.firstSurname" class="control-label col-sm-2">Primer apellido: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="customer.firstSurname" class="form-control" />
	            		</div>
	            </div>
	             <div class="form-group">
	                	<form:label path="customer.secondSurname" class="control-label col-sm-2">Segundo apellido: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="customer.secondSurname" class="form-control"/>
	            		</div>
	            </div>
	            <div class="form-group">
	                	<form:label path="customer.telephone" class="control-label col-sm-2">Teléfono: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="customer.telephone" class="form-control"/>
	                	</div>
	                	<form:errors path="customer.telephone" cssClass="error" />
	            </div>
	            <div class="form-group">	     
	                	<form:label path="customer.email" class="control-label col-sm-2">E-mail: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="customer.email" class="form-control"/>
	                	</div>
	                	<form:errors path="customer.email" cssClass="error" />	            	
	            </div>
	             <div class="form-group">	       
	                	<form:label path="userDetails.username" class="control-label col-sm-2">Username: </form:label>
	                	<div class="col-xs-3">
	                		<form:input path="userDetails.username" class="form-control"/>
	                	</div>
	                	<form:errors path="userDetails.username" cssClass="error" />	            	
	            </div>
	            <div class="form-group">	      
	                	<form:label path="userDetails.password" class="control-label col-sm-2">Contraseña: </form:label>
	                	<div class="col-xs-3">	
	                		<form:input type="password" path="userDetails.password" class="form-control"/>
	                	</div>
	                	<form:errors path="userDetails.password" cssClass="error" />	            	
	            </div>
	            <div class="form-group">
		            <div class="col-sm-offset-2 col-sm-10">	            
		            	<button type="submit" class="btn btn-default">Añadir cliente</button>
		            	<button type="reset" class="btn btn-default">Limpiar</button>
		            	<input type="button" class="btn btn-default" value="Cancelar" onclick="history.back(-1)"/>
		    		</div>
	    		</div>
	    </form:form>
	    
</jsp:body>
</t:paginabasica>
