package br.edu.insper.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.model.DAO;
import br.edu.insper.model.Notas;

/**
 * Servlet implementation class Cria
 */
@WebServlet("/Cria")
public class Cria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao;
		try {
			dao = new DAO();
			List<Notas> notas = dao.getLista();
			request.setAttribute("notas", notas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/listagem.jsp");
			dispatcher.forward(request, response);
			dao.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			DAO dao = new DAO();
			
			Notas nota = new Notas();
			String titulo = request.getParameter("titulo");
			String conteudo = request.getParameter("conteudo");
			Integer prioridade = Integer.valueOf(request.getParameter("prioridade"));
					
			nota.setConteudo(conteudo);
			nota.setTitulo(titulo);
			nota.setPrioridade(prioridade);
			Integer person_id = dao.returnLogado();
			nota.setPerson_id(person_id);
			
			Date today = new Date();
			String data_string = new SimpleDateFormat("yyyy-MM-dd").format(today);
			
			Date data = new SimpleDateFormat("yyyy-MM-dd").parse(data_string);
			Calendar data_ = Calendar.getInstance();
			data_.setTime(data);
			nota.setData(data_);
			
			dao.adiciona(nota);
			
			List<Notas> notas = dao.getLista();
			
			request.setAttribute("notas", notas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/listagem.jsp");
			dispatcher.forward(request, response);
			
			dao.close();		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
