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
<!-- Estils propis -->
<link
    href="${pageContext.request.contextPath}/css/style.css"
    rel="stylesheet">
</head>
<body>
    <header class="container page-header">
        <h1>NaturAdventure</h1>
    </header>
    <t:navegacion />
    <div class="loggeduser"><t:logininfo /></div>
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
