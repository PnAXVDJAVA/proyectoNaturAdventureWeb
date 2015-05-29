<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Editar cliente | NaturAdventure">
<jsp:body>
	
	<h2>Editar cliente</h2>
	    <form:form method="post" modelAttribute="customer" role="form" class="form form-horizontal">
	    
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
                	<form:label path="telephone" class="control-label">Teléfono: </form:label>
                </div>
                <div class="col-xs-4">
	                <div class="col-lg-8">
	                	<form:input path="telephone" class="form-control"/>
	           		</div>
           		</div>
           		<div class="col-xs-3">
                	<form:errors path="telephone" cssClass="error" />
           		</div>
           		<div class="clear"></div>
	        </div>
	    	<div class="form-group">
				<div class="col-xs-2">
                	<form:label path="email" class="control-label">E-mail: </form:label>
                </div>
                <div class="col-xs-4">
	                <div class="col-lg-8">
	                	<form:input path="email" class="form-control"/>
	           		</div>
           		</div>
           		<div class="col-xs-3">
                	<form:errors path="email" cssClass="error" />
           		</div>
           		<div class="clear"></div>
	        </div>	        
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
	                <button class="btn btn-custom btn-padding" type="submit">Confirmar cambios</button>
	                <input type="button" class="btn btn-custom btn-padding" value="Cancelar" onclick="history.back(-1)"/>	                
	            </div>
	        </div>
	    </form:form>
	    
</jsp:body>
</t:paginabasica>
