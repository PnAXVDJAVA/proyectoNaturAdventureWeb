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
		
		<div class="right">
		
			<p>Lista de <span class="negrita">títulos</span> del monitor:</p>
			
			<c:choose>
				<c:when test='${not empty instructorDegrees}'>
					<div class="table-responsive adapter">
						<table class="table table-striped">
							<tr>
								<th>Nombre</th>
							</tr>
							<c:forEach items="${instructorDegrees}" var="instructorDegree">
								<tr>
									<td>${instructorDegree.name}</td>
									<td><a href="${pageContext.request.contextPath}/degree/degreeDetails/${instructorDegree.codDegree}.html"><span class="glyphicon glyphicon-info-sign"></span>  Detalles título</a></td>
									<td><a href="${pageContext.request.contextPath}/instructor/removeTitle.html?codDegree=${instructorDegree.codDegree}&instructorNif=${instructor.nif}"><span class="glyphicon glyphicon-remove"></span>  Borrar título</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<span class="red">El monitor no tiene ningún título.</span>
				</c:otherwise>
			</c:choose>
				
			<hr class="myHr">
			
			<p>Lista de <span class="negrita">títulos disponibles</span>: </p>
				
			<c:choose>
				<c:when test='${not empty degrees}'>
					<div class="table-responsive adapter">
						<table class="table table-striped">
							<tr>
								<th>Nombre</th>
							</tr>
							<c:forEach items="${degrees}" var="degree">
								
								<tr>
									<td>${degree.name}</td>
									<td><a href="${pageContext.request.contextPath}/degree/degreeDetails/${degree.codDegree}.html"><span class="glyphicon glyphicon-info-sign"></span>  Detalles título</a></td>
									<td><a href="${pageContext.request.contextPath}/instructor/addTitle.html?codDegree=${degree.codDegree}&instructorNif=${instructor.nif}"><span class="glyphicon glyphicon-plus"></span>  Añadir título</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<span class="red">No hay títulos disponibles.</span>
				</c:otherwise>
			</c:choose>
			
		</div>
	    
</jsp:body>
</t:paginabasica>
