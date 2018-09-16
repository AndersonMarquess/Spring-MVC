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
</head>
<body>
	<!-- PC = ProdutoController # nome do método que será chamado -->
	<%-- <form:form action="${s:mvcUrl('PC#insert').build() }" method="POST" modelAttribute="produto"> --%>
	<form:form action="/ProjetoSpringMVC/produtos" method="POST" modelAttribute="produto" enctype="multipart/form-data">
		<div>
			<label>Título</label>
			<form:input path="titulo"/>
			<form:errors path="titulo"/>
		</div>
		
		<div>
			<label>Descrição</label>
			<form:textarea rows="3" cols="20" path="descricao"/>
			<form:errors path="descricao"/>
		</div>
		
		<div>
			<label>Páginas</label>
			<form:input path="paginas"/>
			<form:errors path="paginas"/>
		</div>
		
		<div>
			<label>Data de lançamento</label>
			<form:input path="dataLancamento"/>
			<form:errors path="dataLancamento"/>
		</div>
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<form:input path="precos[${status.index}].valor"/>
				<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}"/>
			</div>
		</c:forEach>
		
		<div>
			<label>Sumário</label>
			<input name="sumario" type="file">
		</div>
		
		<button type="submit">Cadastrar</button>
	</form:form>
	
</body>
</html>