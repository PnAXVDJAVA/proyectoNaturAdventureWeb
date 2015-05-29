<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Editar actividad | NaturAdventure">
<jsp:body>
	
	<h2>Editar actividad</h2>
	    <form:form method="post" modelAttribute="activity" role="form" class="form form-horizontal"
	    	accept-charset="UTF-8" enctype="multipart/form-data">
			
			<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="activity.name" class="control-label">Nombre: </form:label>
	                </div>
	                <div class="col-xs-5">
		                <div class="col-lg-7">
		                	<form:input path="activity.name" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="activity.name" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	        </div>
			<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="activity.description" class="control-label">Descripción: </form:label>
	                </div>
	               <div class="col-xs-5">
		                <div class="col-lg-10">
		                	<form:input path="activity.description" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="activity.description" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	            	            
				<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="activity.pricePerPerson" class="control-label">Precio por persona: </form:label>
	                </div>
	                <div class="col-xs-5">
		                <div class="col-lg-3">
		                	<form:input path="activity.pricePerPerson" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="activity.pricePerPerson" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
				<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="activity.duration" class="control-label">Duración: </form:label>
	                </div>
	                <div class="col-xs-5">
		                <div class="col-lg-3">
		                	<form:input path="activity.duration" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="activity.duration" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="activity.minPartakers" class="control-label">Mín. núm. participantes: </form:label>
	                </div>
	                <div class="col-xs-5">
		                <div class="col-lg-3">
		                	<form:input path="activity.minPartakers" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="activity.minPartakers" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           	<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="activity.maxPartakers" class="control-label">Máx. núm. participantes: </form:label>
	                </div>
	                <div class="col-xs-5">
		                <div class="col-lg-3">
		                	<form:input path="activity.maxPartakers" class="form-control"/>
		           		</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="activity.maxPartakers" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
	           		<div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="activity.level" class="control-label">Nivel: </form:label>
	                </div>
	                <div class="col-xs-5">
		                <div class="col-lg-4">
							<form:select path="activity.level" class="form-control">
	                			<form:options items="${levels}" />
	                		</form:select>		           		
	                	</div>
	           		</div>
	           		<div class="col-xs-3">
	                	<form:errors path="activity.level" cssClass="error" />
	           		</div>
	           		<div class="clear"></div>
	           	</div>
			<div class="form-group">
					<div class="col-xs-3">
	                	<label class="control-label">Editar foto: </label>
	                </div>
	                <div class="col-xs-5">
		                <div class="col-lg-3">
							<input id="modify" onClick="changeDisabled()" type="checkbox" name="modificar" class="form-control"/>		           		
						</div>
	           		</div>
	           		<div class="clear"></div>
	        </div>
	        <div class="form-group">
					<div class="col-xs-3">
	                	<form:label path="activity.maxPartakers" class="control-label">Foto: </form:label>
	                </div>
	                <div class="col-xs-5">
		                <div class="col-lg-3">
	                		<form:input id="file" type="file" path="activityPicture.file" name="file" disabled="true"/>
		           		</div>
	           		</div>
	           		<div class="clear"></div>
	        </div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
	                <button class="btn btn-custom" type="submit">Confirmar cambios</button>
	                <button type="reset" class="btn btn-custom">Limpiar</button>
	                <input type="button" class="btn btn-custom" value="Cancelar" onclick="history.back(-1)"/>	                
	            </div>
	        </div>
	                
	    </form:form>
	
		    
	    <script type="text/javascript">
	    	
	    	window.onload = setDisabled();
	    
	    	function setDisabled() {
	    		document.getElementById( "file" ).disabled = true;
	    		document.getElemetById( "modify" ).checked = false;
	    	}
	    
	    	function changeDisabled() {
	    		var disabled = document.getElementById( "file" ).disabled;
	    		if( disabled ) {
	    			document.getElementById( "file" ).disabled = false;
	    		}
	    		else {
	    			document.getElementById( "file" ).disabled = true;
	    		}
	    	}
	    
	    </script>
	    
</jsp:body>
</t:paginabasica>
