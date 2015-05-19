<%@ tag description="Estructura d'una pàgina normal" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<title>${title}</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepickr.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepickr.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleIndex.css">
		<link href='http://fonts.googleapis.com/css?family=Londrina+Solid|Skranji:700' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Open+Sans|Roboto' rel='stylesheet' type='text/css'>
		
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/functions.js"></script>
		<script src="${pageContext.request.contextPath}/js/datepickr.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/datepickr.js"></script>
		<script src="${pageContext.request.contextPath}/js/functionIndex.js"></script>
		
		<script type="text/javascript">
			$('#nav').affix({
			});
		</script>
		
	</head>
	<body>
		<div class="body-wrapper">
		    <header class="headerBackground">
		    	<div class="iconPage"></div>
		        <div class="titlePage">NaturAdventure</div>
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
	    </div>
	</body>
</html>
