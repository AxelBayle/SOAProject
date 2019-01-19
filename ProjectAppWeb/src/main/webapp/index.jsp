<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="COntent-Type" content="text/html" charset="UTF-8">
<title>ProjectWebApiUser</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"
	type="text/javascript"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/mycss.css" />


</head>
<body id="pagetop">

	<nav class="navbar navbar-default navbar-fixed-top"
		style="position: fixed; top: 0px; width: 100%;">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="http://localhost:8080/ProjectAppWeb/index.jsp">It is just fine !</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
				    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Gestion
				    <span class="caret"></span></a>
				    <ul class="dropdown-menu">
					    <li><a href="#Creersalle">Ajouter Salle</a></li>
					    <li><a href="#Controlebat">Action Batiment</a></li>
				    </ul>
			    </li>
			    <li class="dropdown">
				    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Get Infos
				    <span class="caret"></span></a>
				    <ul class="dropdown-menu">
					    <li><a href="#Bat">Infos Batiment</a></li>
					    <li><a href="#Salle">Infos salle</a></li>
				    </ul>
			    </li>
			    <li class="dropdown">
				    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Simulations
				    <span class="caret"></span></a>
				    <ul class="dropdown-menu">
					    <li><a href="#PresenceSalle">Simuler Presence</a></li>
					    <li><a href="#TemperatureSalle">Simuler Temperature</a></li>
				    </ul>
			    </li>
				<li><a href="http://localhost:8080/ProjectAppWeb/login.jsp">Deconnection</a></li>
			</ul>
		</div>
	</nav>

	<div class="container centered" style="text-align: center">
		<h1>Interface Web Utilisateur</h1>
		<div class="row">
			<div class="col-md-4 text-center">
				<div class="panel panel-default" id="Creersalle">
					<div class="panel-body">
						<h3>Creation d'une nouvelle salle</h3>
						<form action="AjouterSalle" method="post">
							<h4 style="color:red;">${ajoutSalle}</h4>
							<p>Etat de la porte : 
								<select name="Porte">
									<option value="1">Deverouillee</option>
									<option value="0">Verouillee</option>
								</select>
							</p>
	
							<p>Etat des fenetre : 
								<select name="Fenetre">
									<option value="1">Ouvertes</option>
									<option value="0">Fermees</option>
								</select>
							</p>
	
							<p>Etat du chauffage : 
								<select name="Chauffage">
									<option value="1">Allume</option>
									<option value="0">Eteint</option>
								</select>
							</p>
	
							<p>Etat des lumières : 
								<select name="Lumiere">
									<option value="1">Allumees</option>
									<option value="0">Eteintes</option>
								</select>
							</p>
							<p>
								<input type="submit" name="submit" value="Creer" />
							</p>
						</form>
					</div>
				</div>
				<div class="panel panel-default" id="Controlebat">
					<div class="panel-body">
						<h3>Controle du batiment</h3>
						<div class="row">
							<div class="col-xs-12">
								<h4 style="color:red;">${actionBat}</h4>
								<form action="VerrouillerBat" method="post">
									<p>
										<input type="submit" name="verbat"
											value="Verouiller le batiment" />
									</p>
								</form>
							</div>
							<div class="col-xs-12">
								<form action="DeverrouillerBat" method="post">
									<p>
										<input type="submit" name="deverbat"
											value="Deverouiller le batiment" />
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4 text-center">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 id="Bat">Recuperation des infos du batiment</h3>
						<form action="AjouterSalle" method="get">
							<p>
								<input type="submit" name="getBat1" value="Check Bat" />
							</p>
							<h4>Etat de l'alarme : ${alarme}</h4>
							<h4>Nombre de salles : ${salle}</h4>
						</form>
						<h3 id="Salle">Recuperation des infos d'une salle</h3>
						<form action="InfoSalle" method="get">
							<p style="text-align: center">Numero de la salle</p>
							<h4 style="color:red;">${ajoutNumero}</h4>
							<p>
								<input type="text" name="idsalle" />
							</p>
							<p>
								<input type="submit" name="getBat2" value="Check info salle" />
							</p>
							<h4>Etat de la porte : ${porte}</h4>
							<h4>Etat de la fenetre : ${fenetre}</h4>
							<h4>Etat du chauffage : ${chauffage}</h4>
							<h4>Etat des lumieres : ${lumiere}</h4>
							<h4>Temperature de la salle : ${temp_in}</h4>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-4 text-center">
				<div class="panel panel-default" id="PresenceSalle">
					<div class="panel-body">
						<h3>Simulateur de presence</h3>
						<div class="row">
							<div class="col-xs-12">
								<form action="GestionPresence" method="post">
									<h4 style="color:red;">${presenceBat}</h4>
									<p>Choisir la salle : 
									<input type="text" name="idsallePresence"/>
									</p>
									<p>Que renvoie le capteur de presence : 
										<select name="Presence">
											<option value="1">Il y a quelqu'un</option>
											<option value="0">Il y a personne</option>
										</select>
									</p>
									<p>
										<input type="submit" name="verbat"
											value="Envoyer" />
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-default" id="TemperatureSalle">
					<div class="panel-body">
						<h3>Simulateur de temperature</h3>
						<div class="row">
							<div class="col-xs-12">
								<form action="GestionTemperature" method="post">
									<h4 style="color:red;">${temperatureBat}</h4>
									<p>Choisir la salle : 
									<input type="text" name="idsalleChauffage"/>
									</p>
									<p>Quelles sont les températures ? </p>
									<p>Temperature exterieure <input type="text" name="TempeOut"/></p>
									<p>Temperature interieure <input type="text" name="TempeIn"/></p>
									<p>
										<input type="submit" name="verbat"
											value="Envoyer" />
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
