package br.edu.insper.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.insper.model.DAO;
import br.edu.insper.model.Notas;

/**
 * Servlet implementation class Atualiza
 */
@WebServlet("/Atualiza")
public class Atualiza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Atualiza() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("ID: <input type='number' name='id'><br>");
		out.println("Título: <input type='text' name='titulo'><br>");
		out.println("Conteúdo: <input type='text' name='conteudo'><br>");
		out.println("Data: <input type='date' name='data'><br>");
		out.println("<input type='submit' value='Atualizar'>");
		out.println("</form>");
		out.println("<body><html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao;
		try {
			dao = new DAO();
			Notas nota = new Notas();
			nota.setId(Integer.valueOf(request.getParameter("id")));
			nota.setTitulo(request.getParameter("titulo"));
			nota.setConteudo(request.getParameter("conteudo"));
			String data = request.getParameter("data");
			Date data_ = new SimpleDateFormat("yyyy-MM-dd").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data_);
			nota.setData(calendar);
			dao.altera(nota);
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("atualizado: " + nota.getTitulo());
			out.println("</body></html>");
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
