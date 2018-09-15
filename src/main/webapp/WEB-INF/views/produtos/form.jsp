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
	<form:form action="/ProjetoSpringMVC/produtos" method="POST" modelAttribute="produto">
		<div>
			<label>Título</label>
			<input type="text" name="titulo">
			<form:errors path="titulo"/>
		</div>
		
		<div>
			<label>Descrição</label>
			<textarea rows="3" cols="20" name="descricao"></textarea>
			<form:errors path="descricao"/>
		</div>
		
		<div>
			<label>Páginas</label>
			<input type="text" name="paginas">
			<form:errors path="paginas"/>
		</div>
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<input type="text" name="precos[${status.index}].valor">
				<input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}">
			</div>
		</c:forEach>
		
		<button type="submit">Cadastrar</button>
	</form:form>
	
</body>
</html>