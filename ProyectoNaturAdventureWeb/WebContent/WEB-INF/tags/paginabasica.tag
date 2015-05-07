<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title}</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
    rel="stylesheet">
<link
    href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"
    rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

<link
    href="${pageContext.request.contextPath}/css/style.css"
    rel="stylesheet">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepickr.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepickr.css">

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/functions.js"></script>
<script src="${pageContext.request.contextPath}/js/datepickr.min.js"></script>
<script src="${pageContext.request.contextPath}/js/datepickr.js"></script>

</head>
<body>
    <header class="container page-header">
        <h1>NaturAdventure</h1>
    </header>
    <t:navegacion />
    <div class="container">
        <jsp:doBody />
    </div>
    <footer>
    <hr>
    <p class="text-muted centered">
    	EI1027 - Diseño e Implementación de Sistemas de Información
    </p>
    </footer>
</body>
</html>
