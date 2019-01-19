<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="COntent-Type" content="text/html" charset="UTF-8">
<title>ProjectWebApiUserLogin</title>

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
<body>
<div class="container centered" style="text-align: center">
		<h1>Interface Web Utilisateur</h1>
		<div class="row">
			<div class="col-md-12 text-center">
				<div class="panel panel-default" id="Creersalle">
					<div class="panel-body">
						<h3>Connection à l'Interface Web Utilisateur</h3>
						<h4 style="color:red;">${connection}</h4>
						<form action="LoginVerify" method="post">
							<p style="text-align: center">Nom d'utilisateur</p>
							<p>
								<input type="text" name="UserName" />
							</p>
	
							<p>Mot de passe</p>
							<p>
								<input type="password" name="pass" />
							</p>
							<p>
								<input type="submit" name="submit" value="Connection" />
							</p>
						</form>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>