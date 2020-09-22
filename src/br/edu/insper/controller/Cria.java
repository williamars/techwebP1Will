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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			DAO dao = new DAO();
			
			Notas nota = new Notas();
			nota.setConteudo(request.getParameter("conteudo"));
			nota.setTitulo(request.getParameter("titulo"));
			Integer person_id = 1;
			nota.setPerson_id(person_id);
			
			Date data = new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-22");
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
