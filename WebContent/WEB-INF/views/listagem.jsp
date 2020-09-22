<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<%@ page import="java.util.*,br.edu.insper.model.*" %>
 
<!DOCTYPE html>
<html>
<head>
	<style>
	.box{
		width: 50%;
	}
	h1 {
		color: #91433f;
	}
	h3 {
		color: black;
	}
	p {
		font-style: italic;
	}
	.title {
		color: #5e1c18;
	}
	.floated {
		float:left;
	    margin-right:30px;
	}
	input[type=submit]{
		background-color: #365e9e;
		border: none;
		color: white;
		padding: 11px 22px;
		border-radius: 10px 5px;
		text-decoration: none;
		margin: 4px 2px;
		cursor: pointer;
	}
	.title {
		width: 60%;
		padding: 12px 20px;
		margin: 8px 0;
	}
	.content {
		width: 60%;
		padding: 12px 50px;
		margin: 8px 0;
	}
	</style>
	<meta charset="ISO-8859-1">
	<title>Notas</title>
</head>
<body>
	<h1>Crie sua nota</h1>
	<form action="Cria" method='post'>
	Titulo <input class="title" type='text' name='titulo'><br>
	Conteudo <input class="content" type='text' name='conteudo'><br>
	<input type='submit' value='Criar'>
	<br>
	<hr color="#91433f" size="3" >
	</form>
	<h1>Notas</h1>
	<c:forEach var="nota" items="${notas}">
		<div class="box">
			<h3>${nota.titulo}</h3>
			<p>${nota.conteudo}</p>
		</div>
		<div>
			<form action='Atualiza' method='get'>
			<input type='hidden' name='id' value="${nota.id}" >
			<input type='hidden' name='titulo' value="${nota.titulo}" >
			<input type='hidden' name='conteudo' value="${nota.conteudo}" >
			<input class="floated" type="submit" value="Editar" >
			</form>
		
			<form action='Remove' method='post'>
			<input type='hidden' name='id' value="${nota.id}" >
			<input class="" type="submit" value="Excluir" >
			<hr size="0.7" align="left" width='50%'>
			</form>
		</div>
 	</c:forEach>
</body>
</html>