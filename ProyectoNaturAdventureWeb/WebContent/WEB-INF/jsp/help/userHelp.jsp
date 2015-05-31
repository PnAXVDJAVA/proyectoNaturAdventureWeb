<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:paginabasica title="Ayuda | NaturAdventure">
<jsp:body>

	<h2>Sección de ayuda</h2>
	
	<div class="info-help-general">
		<div class="info-help-navigator">
			<nav>
				<ul class="nav nav-pills nav-stacked info-help-nav">
					<li role="presentation" class="active"><a>Acceso al Sistema</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpRegistrarse()" role="presentation"><a>Registrarse</a></li>
								<li onclick="setInfoHelpIniciarSesion()" role="presentation"><a>Iniciar sesión</a></li>
								<li onclick="setInfoHelpContrasenyaOlvidada()" role="presentation"><a>Contraseña olvidada</a></li>
							</ul>
						</div>
					</li>

					<li role="presentation" class="active"><a>Sección Actividades</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpConsultarActividades()" role="presentation"><a>Consultar actividades</a></li>
							</ul>
						</div>
					</li>
					<li role="presentation" class="active"><a>Sección Sugerencias</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpSugerencias()" role="presentation"><a>Segurencias, quejas, dudas</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</div>
		<div id="infoHelpText" class="info-help-text"></div>
	</div>
</jsp:body>
</t:paginabasica>