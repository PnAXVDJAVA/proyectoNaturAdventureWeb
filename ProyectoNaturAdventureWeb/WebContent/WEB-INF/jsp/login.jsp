<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<!-- <link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/natacio.css"> -->
<title>Login</title>
</head>
<body>
    <h2>Acceso</h2>
    <form:form method="post" modelAttribute="user"
        action="${pageContext.request.contextPath}/login.html">
        <p>
            <form:label path="username">Nombre de usuario:</form:label>
            <form:input path="username" />
            <form:errors path="username" cssClass="error" />
        </p>
        <p>
            <form:label path="password">Contraseña:</form:label>
            <form:password path="password" />
            <form:errors path="password" cssClass="error" />
        </p>        
        <input type="submit" value="Acceder" />
    </form:form>

</body>
</html>