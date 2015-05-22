<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<d:set var="picture" scope="request" value='${activity.pictureString}'/>
<t:paginabasica title="Gestión de actividades">
<jsp:body>
	
	<h2>Detalles de la actividad</h2>
	    
	    <div class="left">
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Código: </span></div>
		    	<div class="col-sm-5">${activity.codActivity}</div>
		    	<div class="clear"></div>
		    </div>
		    
		   <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre:</span></div>
		    	<div class="col-sm-5">${activity.name}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Descripción:</span></div>
		    	<div class="col-sm-5">${activity.description}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Duración:</span></div>
		    	<div class="col-sm-5">${activity.duration} minutos</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Precio por persona:</span></div>
		    	<div class="col-sm-5">${activity.pricePerPerson} €</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Mín. núm. participantes:</span></div>
		    	<div class="col-sm-5">${activity.minPartakers}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Máx. núm. participantes:</span></div>
		    	<div class="col-sm-5">${activity.maxPartakers}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nivel de dificultad:</span></div>
		    	<div class="col-sm-5">${activity.level}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Foto:</span></div>
		    	<div class="col-sm-5">
		    		<d:choose>
                		<d:when test='${picture != ""}'>
							<img src="data:image/jpeg;base64,${picture}" width="50" height="50">
                		</d:when>
                		<d:otherwise>
                			No hay foto de la actividad
                		</d:otherwise>
	                </d:choose>
				</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/activity/update/${activity.codActivity}.html"><span class="glyphicon glyphicon-pencil"></span>  Editar datos</a></div>
		    	<div class="clear"></div>
		    </div>
		     <div class="formgroup detail-row">
			    <div class="col-sm-5"><a href="${pageContext.request.contextPath}/activity/delete/${activity.codActivity}.html" onclick="return confirm('¿Estás seguro de que quieres borrar la actividad?');"><span class="glyphicon glyphicon-trash"></span>  Eliminar actividad</a></div>
		     	<div class="clear"></div>
		     </div>
		    
		</div>
		
		<div class="right">
			
			
			<p>Lista de <span class="negrita">monitores especializados</span> en la actividad:</p>
		
			<c:choose>
				<c:when test='${not empty addedInstructors}'>
					<div class="table-responsive adapter">
						<table class="table table-striped">
							<tr>
								<th>NIF</th>
								<th>Nombre</th>
							</tr>
							<c:forEach items="${addedInstructors}" var="addedInstructor">
								<tr>
									<td>${addedInstructor.nif}</td>
									<td>${addedInstructor.name}</td>
									<td><a href="${pageContext.request.contextPath}/instructor/instructorDetails/${addedInstructor.nif}.html"><span class="glyphicon glyphicon-info-sign"></span>  Detalles monitor</a></td>
									<td><a href="${pageContext.request.contextPath}/activity/rmsp.html?nif=${addedInstructor.nif}&codActivity=${codActivity}"><span class="glyphicon glyphicon-remove"></span>  Borrar monitor</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<span class="red">No hay monitores especializados en la actividad.</span>
				</c:otherwise>
			</c:choose>
				
			<hr class="myHr">
			
			<p>Lista de <span class="negrita">monitores disponibles</span>: </p>
				
			<c:choose>
				<c:when test='${not empty availableInstructors}'>
					<div class="table-responsive adapter">
						<table class="table table-striped">
							<tr>
								<th>NIF</th>
								<th>Nombre</th>
							</tr>
							<c:forEach items="${availableInstructors}" var="availableInstructor">
								
								<tr>
									<td>${availableInstructor.nif}</td>
									<td>${availableInstructor.name}</td>
									<td><a href="${pageContext.request.contextPath}/instructor/instructorDetails/${availableInstructor.nif}.html"><span class="glyphicon glyphicon-info-sign"></span>  Detalles monitor</a></td>
									<td><a href="${pageContext.request.contextPath}/activity/addsp.html?nif=${availableInstructor.nif}&codActivity=${codActivity}"><span class="glyphicon glyphicon-plus"></span>  Añadir monitor</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<span class="red">No hay monitores disponibles.</span>
				</c:otherwise>
			</c:choose>
			
		</div>
	    
</jsp:body>
</t:paginabasica>
