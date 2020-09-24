<%@page import="java.text.SimpleDateFormat"%>
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
		margin: -10px 0 10px 0;
	}
	.content {
		width: 60%;
		height: 150px;
		padding: 12px 20px;
		margin: -10px 0 10px 0;
	}
	.btnStyle {
		display: flex;
		justify-content: flex-end;
	}
	</style>
	<meta charset="ISO-8859-1">
	<title>Notas</title>
</head>
<body>
	<h1>Crie sua nota</h1>
	<form action="Cria" method='post'>
	<h4>Titulo</h4>
	<input class="title" placeholder="digite seu titulo..." type='text' name='titulo'><br>
	<h4>Conteudo</h4>
	<input class="content" placeholder="digite o que voce esta pensando..." type='text' name='conteudo'><br>
	<h4>Prioridade</h4>
	<input class="title" placeholder="digite a prioridade, de 1 a 3..." type="number" name='prioridade'><br>
	<input type='submit' value='Criar' class="btnStyle">
	<br>
	<hr color="#91433f" size="3" >
	</form>
	
	
	
	<h1 class="floated">Notas... ou</h1>
	<form action="Pesquisa" method="post">
		<input placeholder="pesquise por titulo..." type='text' name='titulo'><br>
		<input type='submit' value='pesquisar'><br>
	</form>
	<h1 class="floated" style="margin-left: 300px; margin-bottom: 30px;">ou...</h1>
	<form action="OrderPrioridade" method="post">
		<input type='submit' value='ordernar por prioridade [1-3]'><br>
	</form>
	<form action="Order" method="post">
		<input type='submit' value='ordenar por data [old-new]'><br>
	</form>
	<form action="Lista">
		<input style="margin-left: 565px;" type='submit' value='listar normalmente'><br>
	</form>
	<c:forEach var="nota" items="${notas}">
			
		<div class="box">
			<h3>${nota.titulo}</h3>
			<p>${nota.conteudo}</p>
			<p style='color: red;'>prioridade: ${nota.prioridade}</p>
			<div>
				<p style="font-weight: bold; font-size:15px">
				data de criacao/edicao: <fmt:formatDate value='${nota.data.time}' pattern='dd-MM-yyyy' />
				</p>
			</div>
		</div>
		<div>
			<form  action='Atualiza' method='get'>
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
 	</c:forEach>
 	<h1>Categorizacao</h1>
 	<p>Quer ver apenas as tarefas mais importantes pra voce? Selecione sua prioridade!</p>
 	<form action='Filtra' method='post'>
 	<input type='submit' name='prioridade' value='1' > <br>
 	<input type='submit' name='prioridade' value='2' > <br>
 	<input type='submit' name='prioridade' value='3' > <br>
 	</form>
 	
</body>
</html>