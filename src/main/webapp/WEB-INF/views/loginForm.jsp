<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!-- Usado para exibir a field.required -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<title>Livros - Casa do Código</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
		integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	
	<div class="container">
	
		<h2>Login ProjetoSpringMVC</h2>
		
		<form:form servletRelativeAction="/login" method="POST">
			<div class="form-group">
				<label>Email</label>
				<input name="username" type="text" class="form-control"/>
			</div>
			
			<div class="form-group">
				<label>Senha</label>
				<input name="password" type="password" class="form-control"/>
			</div>
			
			<button type="submit" class="btn btn-outline-success">Entrar</button>
		</form:form>
	</div>
</body>
</html>