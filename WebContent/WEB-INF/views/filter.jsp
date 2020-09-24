<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<%@ page import="java.util.*,br.edu.insper.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>categorizacao</title>
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
	p{
		font-style: italic;
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
	</style>
</head>
<body>
	
	<form action="Lista">
	<input type='submit' value="voltar" >
	</form>
	<h1>notas filtradas pela sua prioridade</h1>
	<% Integer contador = 0; %>
	<c:forEach var="nota" items="${notas}">
		<div class="box">
			<h3>${nota.titulo}</h3>
			<p>${nota.conteudo}</p>
			<p style='color: red;'>prioridade: ${nota.prioridade}</p>
		</div>
		<div>
			<form action='Atualiza' method='get'>
			<input type='hidden' name='id' value="${nota.id}" >
			<input type='hidden' name='titulo' value="${nota.titulo}" >
			<input type='hidden' name='conteudo' value="${nota.conteudo}" >
			<input type='hidden' name='data' value="${nota.data}" >
			<input type='hidden' name='prioridade' value="${nota.prioridade}" >
			<input class="floated" type="submit" value="Editar" >
			</form>

			<form action='Remove' method='post'>
			<input type='hidden' name='id' value="${nota.id}" >
			<input class="" type="submit" value="Excluir" >
			<hr size="0.7" align="left" width='50%'>
			</form>
		</div>
		<% contador++; %>
 	</c:forEach>
 	<%
 	if (contador == 0){
 		out.print("não ha notas nessa prioridade");
 	}
 	%>

</body>
</html>