<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8" />
		<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/natacio.css">  -->
		<title>Crear nuevo t�tulo</title>
	</head>
	
	<body>
	
	<h2>Nuevo t�tulo</h2>
	    <form:form method="post" modelAttribute="degree">
	        <table>
	        	 <tr>
	                <td><form:label path="codDegree">C�digo t�tulo: </form:label></td>
	                <td><form:input path="codDegree" /></td>
	                <td><form:errors path="codDegree" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="name">Nombre: </form:label></td>
	                <td><form:input path="name" /></td>
	                <td><form:errors path="name" cssClass="error" /></td>
	            </tr>
	            <tr>
	                <td><form:label path="description">Descripci�n: </form:label></td>
	                <td><form:input path="description" /></td>
	            </tr>
	            <tr>
	                <td colspan="2"><input type="submit" value="A�adir t�tulo" />
	                </td>
	            </tr>
	        </table>
	    </form:form>
	    
	</body>
	
</html>
