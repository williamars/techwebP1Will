package br.edu.insper.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * Servlet implementation class Lista
 */
@WebServlet("/Lista")
public class Lista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lista() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest request,
    		 HttpServletResponse response)
    		 throws ServletException, IOException {

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
}