<%@ tag description="Estructura d'una pàgina normal"
    pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
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

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

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
