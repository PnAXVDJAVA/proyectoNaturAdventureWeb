<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="status" scope="request" value='${booking.status}'/> 
<t:paginabasica title="Información de la reserva | NaturAdventure">
<jsp:body>
	
	<h2>Detalles de la reserva</h2>
	    
	    <div class="left">
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Código de reserva: </span></div>
		    	<div class="col-sm-5">${booking.codBooking}</div>
		    	<div class="clear"></div>
		    </div>
		    
		   <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Fecha propuesta:</span></div>
		    	<div class="col-sm-5">${booking.proposalPerformingDateString}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Hora de comienzo:</span></div>
		    	<div class="col-sm-5">${booking.startHour}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Fecha de reserva:</span></div>
		    	<div class="col-sm-5">${booking.bookingDateString}</div>
		    	<div class="clear"></div>
		    </div>
		    
			<div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Número de participantes:</span></div>
		    	<div class="col-sm-5">${booking.numPartakers}</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre del cliente:</span></div>
		    	<div class="col-sm-5"><a href="${pageContext.request.contextPath}/customer/customerDetails/${booking.customerNif}.html">${booking.customerName}</a></div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Nombre de la actividad:</span></div>
		    	<div class="col-sm-5"><a href="${pageContext.request.contextPath}/activity/activityDetails/${booking.codActivity}.html">${booking.activityName}</a></div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><span class="negrita">Estado:</span></div>
		    	<div class="col-sm-5">
		    		<c:choose>
            			<c:when test='${status == "pending" }'>
             				<span class="orange">${booking.status}</span>
             			</c:when>
             			<c:when test='${status == "accepted" }'>
             				<span class="green">${booking.status}</span>
             			</c:when>
             			<c:when test='${status == "denied" }'>
             				<span class="red">${booking.status}</span>
             			</c:when>
       				</c:choose>
		    	</div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><a href="${pageContext.request.contextPath}/booking/update/${booking.codBooking}.html"><span class="glyphicon glyphicon-pencil"></span>  Editar datos</a></div>
		    	<div class="clear"></div>
		    </div>
		    
		    <div class="formgroup detail-row">
		    	<div class="col-sm-5"><a href="${pageContext.request.contextPath}/booking/delete/${booking.codBooking}.html" onclick="return confirm('¿Estás seguro de que quieres borrar la reserva?');"><span class="glyphicon glyphicon-trash"></span>  Eliminar reserva</a></div>
		    	<div class="clear"></div>
		    </div>
		    
		    <c:choose>
	           <c:when test='${status == "pending"}'>
				    <div class="formgroup detail-row">
				    	<hr class="myHr">
				    	<div class="col-sm-5">
				    		<a href="${pageContext.request.contextPath}/booking/accept/${booking.codBooking}.html" onclick="return confirm('¿Estás seguro de que quieres aceptar la reserva? Se enviará en email de aceptación al cliente que la solicitó.');"><span class="glyphicon glyphicon-ok"></span>  Aceptar reserva</a>
				    	</div>
				    	
				    	<div class="col-sm-5">
				    		<a href="${pageContext.request.contextPath}/booking/deny/${booking.codBooking}.html" onclick="return confirm('¿Estás seguro de que quieres rechazar la reserva? Se enviará en email de rechazo al cliente que la solicitó.');"><span class="glyphicon glyphicon-remove"></span>  Rechazar reserva</a>
				    	</div>
				    </div>
		    	</c:when>
		    </c:choose>
		    
		</div>
		
		<c:choose>
			
			<c:when test='${booking.status != "denied" }'>
			
				<div class="right">
				
					<p>Lista de monitores asignados a la <span class="negrita">reserva</span>:</p>
					
					<c:choose>
						<c:when test='${not empty assignedInstructors}'>
							<div class="table-responsive adapter">
									<table class="table table-striped">
										<tr>
											<th>NIF</th>
											<th>Nombre</th>
										</tr>
										<c:forEach items="${assignedInstructors}" var="assignedInstructor">
											<tr>
												<td>${assignedInstructor.nif}</td>
												<td>${assignedInstructor.name}</td>
												<td><a href="${pageContext.request.contextPath}/instructor/instructorDetails/${addedInstructor.nif}.html"><span class="glyphicon glyphicon-info-sign"></span>  Detalles monitor</a></td>
												<c:choose>
													<c:when test='${booking.status == "pending" }'>
														<td><a href="${pageContext.request.contextPath}/booking/removeInstructor.html?nif=${assignedInstructor.nif}&codBooking=${codBooking}"><span class="glyphicon glyphicon-remove"></span>  Borrar monitor</a></td>
													</c:when>
												</c:choose>
											</tr>
										</c:forEach>
									</table>
							</div>
						</c:when>
						<c:otherwise>
							<span class="red">No hay monitores asignados.</span>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test='${booking.status == "pending" }'>
						
							<hr class="myHr">
							
							<p>Lista de monitores asignados a la <span class="negrita">actividad</span>: </p>
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
													<td><a href="${pageContext.request.contextPath}/instructor/instructorDetails/${addedInstructor.nif}.html"><span class="glyphicon glyphicon-info-sign"></span>  Detalles monitor</a></td>
													<td><a href="${pageContext.request.contextPath}/booking/assignInstructor.html?nif=${availableInstructor.nif}&codBooking=${codBooking}"><span class="glyphicon glyphicon-plus"></span>  Añadir monitor</a></td>
												</tr>
											</c:forEach>
									
										</table>
									</div>
								</c:when>
								<c:otherwise>
									<span class="red">No hay monitores disponibles.</span>
								</c:otherwise>
							</c:choose>
							
						</c:when>
					</c:choose>
					
				</div>
			
			</c:when>
		
		</c:choose>
	    
</jsp:body>
</t:paginabasica>
