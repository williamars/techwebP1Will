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
		 
		String sql = "SELECT Nota.id, Nota.conteudo, Nota.titulo, Nota.data, Nota.person_id, Nota.prioridade FROM Nota INNER JOIN Login ON Nota.person_id=Login.id AND Login.logado=1";
		
		//String sql = "SELECT * from nota";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
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
		Integer idUser = returnLogado();
		PreparedStatement stmt = connection.prepareStatement("SELECT N.id, n.conteudo, n.titulo, n.data, n.person_id, n.prioridade "
				+ "from Nota n inner join login on n.prioridade=? and login.id=n.person_id AND login.id=?");
		stmt.setInt(1, prioridade);
		stmt.setInt(2, idUser);
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
		Integer idUser = returnLogado();
		PreparedStatement stmt = connection.prepareStatement("select n.id, n.conteudo, n.titulo, "
				+ "n.data, n.person_id, n.prioridade from nota n inner join login l on l.id=n.person_id "
				+ "and l.id=? and n.titulo like ? ");
		stmt.setInt(1, idUser);
		stmt.setString(2,"%" + title + "%");
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
		Integer idUser = returnLogado();
		PreparedStatement stmt = connection.prepareStatement("SELECT N.id, n.conteudo, n.titulo, n.data, n.person_id,"
				+ " n.prioridade from Nota n inner join login l on l.id=n.person_id and l.id=? order by n.data;");
		stmt.setInt(1, idUser);
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
		Integer idUser = returnLogado();
		PreparedStatement stmt = connection.prepareStatement("SELECT N.id, n.conteudo, n.titulo, n.data, n.person_id,"
				+ " n.prioridade from Nota n inner join login l on l.id=n.person_id and l.id=? order by n.prioridade;");
		stmt.setInt(1, idUser);
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
	
	
	public List<Login> getUsers() throws SQLException{
		List<Login> users = new ArrayList<Login>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Login");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Login login = new Login();
			login.setId(rs.getInt("id"));
			login.setUser(rs.getString("user"));
			login.setSenha(rs.getString("senha"));
			login.setLogado(rs.getInt("logado"));
			
			users.add(login);			
		}
		
		rs.close();
		stmt.close();
		
		return users;
		
	}
	
	public void setLogado(String user) throws SQLException {
		String sql = "UPDATE Login SET logado=0 WHERE user!=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, user);
		stmt.execute();
		stmt.close();
		
		String sql2 = "UPDATE Login SET logado=1 WHERE user=?";
		PreparedStatement s = connection.prepareStatement(sql2);
		s.setString(1, user);
		s.execute();
		s.close();
	}
	
	public Integer returnLogado() throws SQLException {
		Integer idUser = 0;
		String sql = "SELECT id FROM Login WHERE logado=1";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {		
			idUser = rs.getInt("id");
		}
		
		
		stmt.close();
		
		
		
		return idUser;
	}

	
}
