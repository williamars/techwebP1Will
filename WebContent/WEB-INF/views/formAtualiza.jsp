<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<%@ page import="java.util.*,br.edu.insper.model.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Atualizar Nota</title>
	<style>
	h4 {
		color: #5e1c18;
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
		margin: -10px 0 10px 0;
	}
	.content {
		width: 60%;
		height: 150px;
		padding: 12px 20px;
		margin: -10px 0 10px 0;
	}
	</style>
</head>
<body>
<%
	Date today = new Date();
	String data = new SimpleDateFormat("yyyy-MM-dd").format(today);
%>
	<form action='Atualiza' method='post'>
		<h4>Título</h4>
		<input class="title" type='text' name='titulo' value='${param.titulo}'><br>
		<h4>Conteúdo</h4>
		<input class="content" type='text' name='conteudo' value='${param.conteudo}'><br>
		<h4>Prioridade</h4>
		<input class="title" type='number' name='prioridade' value='${param.prioridade}'> <br>
		<input type='hidden' name='data' value='<%=data%>'> <br>
		<input type='hidden' name='id' value='${param.id}' ><br>
		<input type='submit' value='Atualizar'>
	</form>
</body>
</html>