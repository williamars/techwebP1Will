package br.edu.insper.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DAO {
	private Connection connection = null;
	public DAO() throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager.getConnection(
		"jdbc:mysql://localhost/projeto", "will", "804074");
	}
	
	public List<Notas> getLista() throws SQLException{
		
		List<Notas> notas = new ArrayList<Notas>();
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Nota");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Notas nota = new Notas();
			nota.setId(rs.getInt("id"));
			nota.setConteudo(rs.getString("conteudo"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data"));
			nota.setData(data);
			nota.setTitulo(rs.getString("titulo"));
			nota.setPerson_id(rs.getInt("person_id"));
			nota.setPrioridade(rs.getInt("prioridade"));
			notas.add(nota);
		}
		
		rs.close();
		stmt.close();
		
		return notas;	
	}
	
	public void close() throws SQLException {
		connection.close();
	}
	
	public void adiciona(Notas nota) throws SQLException {
		String sql = "INSERT Into Nota" + "(conteudo, titulo, data, person_id, prioridade) values (?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, nota.getConteudo());
		stmt.setString(2, nota.getTitulo());
		stmt.setDate(3, new java.sql.Date(nota.getData().getTimeInMillis()));
		stmt.setInt(4, nota.getPerson_id());
		stmt.setInt(5, nota.getPrioridade());
		
		stmt.execute();
		stmt.close();		
	}
	
	public void altera(Notas nota) throws SQLException {
		String sql = "UPDATE Nota SET " + "titulo=?, conteudo=?, data=?, prioridade=? WHERE id=?" ;
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, nota.getTitulo());
		stmt.setString(2, nota.getConteudo());
		stmt.setDate(3, new java.sql.Date(nota.getData().getTimeInMillis()));
		stmt.setInt(4, nota.getPrioridade());
		stmt.setInt(5, nota.getId());
		
		stmt.execute();
		stmt.close();		
	}
	
	public void remove(Integer id) throws SQLException {
		String sql = "DELETE FROM Nota WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}
	
	public List<Notas> filtra(Integer prioridade) throws SQLException{
		
		List<Notas> notas = new ArrayList<Notas>();
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Nota WHERE prioridade=?");
		stmt.setInt(1, prioridade);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Notas nota = new Notas();
			nota.setId(rs.getInt("id"));
			nota.setConteudo(rs.getString("conteudo"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data"));
			nota.setData(data);
			nota.setTitulo(rs.getString("titulo"));
			nota.setPerson_id(rs.getInt("person_id"));
			nota.setPrioridade(rs.getInt("prioridade"));
			notas.add(nota);
		}
		
		rs.close();
		stmt.close();
		
		return notas;	
	}
	
	public List<Notas> pesquisa(String title) throws SQLException{
		
		
		List<Notas> notas = new ArrayList<Notas>();
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Nota WHERE titulo LIKE ?");
		stmt.setString(1,"%" + title + "%");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Notas nota = new Notas();
			nota.setId(rs.getInt("id"));
			nota.setConteudo(rs.getString("conteudo"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data"));
			nota.setData(data);
			nota.setTitulo(rs.getString("titulo"));
			nota.setPerson_id(rs.getInt("person_id"));
			nota.setPrioridade(rs.getInt("prioridade"));
			notas.add(nota);
		}
		
		rs.close();
		stmt.close();
		
		return notas;	
	}
	
	public List<Notas> order() throws SQLException{
		
		List<Notas> notas = new ArrayList<Notas>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Nota ORDER BY data");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Notas nota = new Notas();
			nota.setId(rs.getInt("id"));
			nota.setConteudo(rs.getString("conteudo"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data"));
			nota.setData(data);
			nota.setTitulo(rs.getString("titulo"));
			nota.setPerson_id(rs.getInt("person_id"));
			nota.setPrioridade(rs.getInt("prioridade"));
			notas.add(nota);
		}
		
		rs.close();
		stmt.close();
		
		return notas;	
	}
	
public List<Notas> orderPrioridade() throws SQLException{
		
		List<Notas> notas = new ArrayList<Notas>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Nota ORDER BY prioridade");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Notas nota = new Notas();
			nota.setId(rs.getInt("id"));
			nota.setConteudo(rs.getString("conteudo"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("data"));
			nota.setData(data);
			nota.setTitulo(rs.getString("titulo"));
			nota.setPerson_id(rs.getInt("person_id"));
			nota.setPrioridade(rs.getInt("prioridade"));
			notas.add(nota);
		}
		
		rs.close();
		stmt.close();
		
		return notas;	
	}
	

	
}
