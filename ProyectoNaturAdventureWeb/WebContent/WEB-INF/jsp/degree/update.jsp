<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Editar título | NaturAdventure">
<jsp:body>
	
	<h2>Editar título</h2>
	    <form:form method="post" modelAttribute="degree" role="form" class="form form-horizontal"
	    	accept-charset="UTF-8" enctype="multipart/form-data">
			
			<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="name" class="control-label">Nombre: </form:label>
	                </div>
	                <div class="col-xs-5">
		                <div class="col-lg-7">
		                	<form:input path="name" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="name" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	        </div>
			<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="description" class="control-label">Descripción: </form:label>
	                </div>
	               <div class="col-xs-5">
		                <div class="col-lg-10">
		                	<form:input path="description" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="description" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
           	</div>
			<div class="form-group">
					<div class="col-xs-3"></div>
					<div class="col-xs-6">
		                <button class="btn btn-custom btn-padding" type="submit">Confirmar cambios</button>
		                <button type="reset" class="btn btn-custom btn-padding">Restaurar valores</button>
		                <input type="button" class="btn btn-custom btn-padding" value="Cancelar" onclick="history.back(-1)"/>	                
			        </div>
	        </div>
	                
	    </form:form>
	
</jsp:body>
</t:paginabasica>
