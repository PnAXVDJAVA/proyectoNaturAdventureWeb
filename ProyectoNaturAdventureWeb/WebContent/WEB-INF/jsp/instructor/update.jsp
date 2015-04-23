<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8" />
		<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/natacio.css"> -->
		<title>Editar monitor</title>
	</head>
	
	<body>
	
	<h2>Editar monitor</h2>
	    <form:form method="post" modelAttribute="instructor">
	        <table>
	            <tr>
	                <td><form:label path="nif">NIF: </form:label></td>
	                <td><form:input path="nif" value="${instructor.nif}" readonly="true"/></td>
	                <td><form:errors path="nif" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="name">Nombre: </form:label></td>
	                <td><form:input path="name" /></td>
	                <td><form:errors path="name" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="firstSurname">Primer apellido: </form:label></td>
	                <td><form:input path="firstSurname" /></td>
	            </tr>
	             <tr>
	                <td><form:label path="secondSurname">Segundo apellido: </form:label></td>
	                <td><form:input path="secondSurname" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="address">Dirección: </form:label></td>
	                <td><form:input path="address" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="telephone">Teléfono: </form:label></td>
	                <td><form:input path="telephone" /></td>
	                <td><form:errors path="telephone" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="dateOfBirthString">Fecha de cumpleaños: ( DD/MM/AAAA ) </form:label></td>
	                <td><form:input path="dateOfBirthString" /></td>
	                <td><form:errors path="dateOfBirthString" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="email">E-mail: </form:label></td>
	                <td><form:input path="email" /></td>
	                <td><form:errors path="email" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="bankAccount">Cuenta bancaria: </form:label></td>
	                <td><form:input path="bankAccount" /></td>
	                <td><form:errors path="bankAccount" cssClass="error" /></td>
	            </tr>
	             <tr>
	                <td><form:label path="userID">User ID: </form:label></td>
	                <td><form:input path="userID" /></td>
	                <td><form:errors path="userID" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="password">Contraseña: </form:label></td>
	                <td><form:input path="password" /></td>
	                <td><form:errors path="password" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td colspan="2"><input type="submit" value="Confirmar cambios" />
	                </td>
	            </tr>
	        </table>
	    </form:form>
	    
	</body>
	
</html>
