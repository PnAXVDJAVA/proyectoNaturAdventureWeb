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
					<li role="presentation" class="active"><a>Sección Actividades</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpConsultarActividadesAdmin()" role="presentation"><a>Consultar actividades</a></li>
								<li onclick="setInfoHelpNuevaActividadAdmin()" role="presentation"><a>Nueva actividad</a></li>
							</ul>
						</div>
					</li>
					
					<li role="presentation" class="active"><a>Sección Cliente</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpConsultarClientesAdmin()" role="presentation"><a>Consultar clientes</a></li>
								<li onclick="setInfoHelpNuevoClienteAdmin()" role="presentation"><a>Nuevo cliente</a></li>
							</ul>
						</div>
					</li>
					
					<li role="presentation" class="active"><a>Sección Monitores</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpConsultarMonitoresAdmin()" role="presentation"><a>Consultar monitores</a></li>
								<li onclick="setInfoHelpNuevoMonitorAdmin()" role="presentation"><a>Nuevo monitor</a></li>
								<li onclick="setInfoHelpConsultarTitulosAdmin()" role="presentation"><a>Consultar títulos de monitores</a></li>
								<li onclick="setInfoHelpNuevoTituloAdmin()" role="presentation"><a>Nuevo título de monitor</a></li>
							</ul>
						</div>
					</li>
					
					<li role="presentation" class="active"><a>Sección Reservas</a></li>
					<li>
						<div class="info-help-subnav">
							<ul class="nav nav-pills nav-stacked">
								<li onclick="setInfoHelpConsultarReservasAdmin()" role="presentation"><a>Consultar reservas</a></li>
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