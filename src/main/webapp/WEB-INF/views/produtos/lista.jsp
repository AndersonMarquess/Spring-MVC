<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<title>Livros - Casa do Código</title>
	<link rel="stylesheet" 
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
		crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="/ProjetoSpringMVC/">Projeto Spring MVC</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
	   aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link" href="/ProjetoSpringMVC/produtos">Listar produtos <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/ProjetoSpringMVC/produtos/form">Cadastrar produto</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/ProjetoSpringMVC/logout">Sair</a>
	      </li>
	    </ul>
	    <ul class="navbar-nav ml-auto">
	    	<li>
	    		<a class="nav-link" href="#">
	    			<!-- Mostra o nome de usuário logado --> 
	    			<security:authentication property="principal" var="usuario"/>
	    			<c:if test="usuario.username != null">
	    				Usuário: ${usuario.username }
	    			</c:if>
    			</a>
	    	</li>
	    </ul>
	  </div>
	</nav>
	
	<div class="container" style="margin-top:50px; margin-bottom:60px">
		<h2>Lista de produtos</h2>
		
		<div>
			${sucesso}
		</div>
		<div>
			${falha}
		</div>
		
		<table class="table table-bordered">
			<tr>
				<th>Título</th>
				<th>Descrição</th>
				<th>Páginas</th>
			</tr>
			
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><a href="/ProjetoSpringMVC/produtos/detalhe/${produto.id}">${produto.titulo}</a></td>
					<td>${produto.descricao}</td>
					<td>${produto.paginas}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>