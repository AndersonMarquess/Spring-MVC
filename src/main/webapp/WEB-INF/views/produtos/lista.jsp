<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Livros - Casa do Código</title>
</head>
<body>
	
	<h2>Lista de produtos</h2>
	
	<div>
		${sucesso}
	</div>
	<div>
		${falha}
	</div>
	
	<table>
		<tr>
			<td>Título</td>
			<td>Descrição</td>
			<td>Páginas</td>
		</tr>
		
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td><a href="/ProjetoSpringMVC/produtos/detalhe/${produto.id}">${produto.titulo}</a></td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>