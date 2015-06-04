<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d"%>
<d:set var="picture" scope="request" value='${activity.pictureString}'/>
<t:paginabasica title="Información de actividad | NaturAdventure">
<jsp:body>
	
	<h2>Detalles de la actividad</h2>
	    
	    <div class="left">
		    
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
							<img src="data:image/jpeg;base64,${picture}" width="200" height="200">
                		</d:when>
                		<d:otherwise>
                			No hay foto de la actividad
                		</d:otherwise>
	                </d:choose>
				</div>
		    	<div class="clear"></div>
		    </div>
		    
		</div>
	    
</jsp:body>
</t:paginabasica>
