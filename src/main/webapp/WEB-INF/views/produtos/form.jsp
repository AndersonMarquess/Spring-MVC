<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="/ProjetoSpringMVC/">Projeto Spring MVC</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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
	    </ul>
	  </div>
	</nav>
	
	<div class="container" style="margin-top:50px; margin-bottom:60px">
	
		<h2>Cadastro de Produto</h2>

		<!-- PC = ProdutoController # nome do método que será chamado -->
		<%-- <form:form action="${s:mvcUrl('PC#insert').build() }" method="POST" modelAttribute="produto"> --%>
		<form:form action="/ProjetoSpringMVC/produtos" method="POST" modelAttribute="produto" enctype="multipart/form-data">
			<div class="form-group">
				<label>Título</label>
				<form:input path="titulo" cssClass="form-control"/>
				<form:errors path="titulo"/>
			</div>
			
			<div class="form-group">
				<label>Descrição</label>
				<form:textarea rows="3" cols="20" path="descricao" cssClass="form-control"/>
				<form:errors path="descricao"/>
			</div>
			
			<div class="form-group">
				<label>Páginas</label>
				<form:input path="paginas" cssClass="form-control"/>
				<form:errors path="paginas"/>
			</div>
			
			<div class="form-group">
				<label>Data de lançamento</label>
				<form:input path="dataLancamento" cssClass="form-control"/>
				<form:errors path="dataLancamento"/>
			</div>
			
			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
				<div class="form-group">
					<label>${tipoPreco}</label>
					<form:input path="precos[${status.index}].valor" cssClass="form-control"/>
					<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}"/>
				</div>
			</c:forEach>
			
			<div class="form-group">
				<label>Sumário</label>
				<input name="sumario" type="file" class="btn btn-outline-secondary">
			</div>
			
			<button type="submit" class="btn btn-outline-success">Cadastrar</button>
		</form:form>
	</div>
</body>
</html>