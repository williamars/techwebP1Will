package br.edu.insper.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			DAO dao = new DAO();
			
			String user = request.getParameter("user");
			String senha = request.getParameter("senha");
			br.edu.insper.model.Login usuario = new br.edu.insper.model.Login();			
			usuario.setUser(user);
			usuario.setSenha(senha);
			
			
			List<br.edu.insper.model.Login> users = new ArrayList<br.edu.insper.model.Login>();
			users = dao.getUsers();
			boolean cadastrado = false;
			
			for (br.edu.insper.model.Login each : users) {
				if (each.getUser().contentEquals(usuario.getUser()) && each.getSenha().contentEquals(usuario.getSenha())) {
					cadastrado = true;
				}
			}
			
			
			if (cadastrado) {
				dao.setLogado(usuario.getUser());
				
				List<Notas> notas = dao.getLista();
				request.setAttribute("notas", notas);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/listagem.jsp");
				dispatcher.forward(request, response);			
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.html");
				dispatcher.forward(request, response);
			}
			
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
