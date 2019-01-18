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
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>


	<div class="container centered" style="text-align: center">
		<h1 type="text-centered">Interface Web Utilisateur</h1>
		<div class="panel panel-default">
			<div class="panel-body">
					<h3>Creation d'une nouvelle salle</h3>
				<form action="AjouterSalle" method="post">
					<p style="text-align: center">
						Nom d'utilisateur</p>
					<p> <input type="text" name="UserName" />
					</p>

					<p>
						Mot de passe</p>
					<p> <input type="password" name="pass" />
					</p>


					<p>Etat de la porte</p>
					<p>
						<select name="Porte">
							<option value="1">Deverouillee</option>
							<option value="0">Verouillee</option>
						</select>
					</p>

					<p>
						Etat des fenetre</p>
					<p> <select name="Fenetre">
							<option value="1">Ouvertes</option>
							<option value="0">Fermees</option>
						</select>
					</p>

					<p>
						Etat du chauffage</p>
					<p> <select name="Chauffage">
							<option value="1">Allume</option>
							<option value="0">Eteint</option>
						</select>
					</p>

					<p>
						Etat des lumières</p>
					<p> <select name="Lumiere">
							<option value="1">Allumees</option>
							<option value="0">Eteintes</option>
						</select>
					</p>
						<p>
							Creer une nouvelle salle avec ces parametres</p>
					<p> <input type="submit"
								name="submit" value="Creer" />
						</p>
				</form>
			</div>
		</div>
		<hr>

		<div class="panel panel-default">
			<div class="panel-body">
					<h3>Recuperation des infos du batiment</h3>
					<form action="AjouterSalle" method="get">
						<p>
							<input type="submit" name="getBat" value="check Bat" />
						</p>
					</form>
			</div>
		</div>
	</div>

</body>
</html>
