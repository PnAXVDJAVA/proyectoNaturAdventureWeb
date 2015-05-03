<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Prueba">
<jsp:body>
			
		<form>
			<label>NIF: </label>
			<input id="nif" name="nif" type="text">
			<br>
			<label>Name: </label>
			<input id="name" name="name" type="text">
		</form>	
		<button class="btn btn-default btn-padding" onClick="test()">Añadir monitor</button>
		
		<form:form method="post" modelAttribute="instructorList" role="form">
		
			<table id="table">
		
				<tr>
					<th>NIF</th>
					<th>Name</th>
				</tr>
				
				<c:forEach items="${instructorList.list}" var="instructor" varStatus="status">
				
					<tr>
						<td><form:input path="list[${status.index}].nif" type="text" /></td>
					</tr>
				
				</c:forEach>
		
			</table>
		
			<button type="submit" class="btn btn-default btn-padding">Prueba</button>
		</form:form>			

</jsp:body>
</t:paginabasica>