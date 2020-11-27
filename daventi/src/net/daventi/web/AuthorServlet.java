package net.daventi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daventi.dao.AuthorDAO;
import net.daventi.model.Author;

/**
 * Servlet implementation class AuthorServlet
 */
@WebServlet("/")
public class AuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthorDAO authorDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		authorDao = new AuthorDAO();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch(action) {
			case "/author/new":
				showNewForm(request, response);
				break;
			case "/author/insert":
				insertAuthor(request, response);
				break;
			case "/author/delete":
				deleteAuthor(request, response);
				break;
			case "/author/edit":
				showEditForm(request, response);
				break;
			case "/author/update":
				updateAuthor(request, response);
				break;
			default:
				listAuthor(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("author-form.jsp");
		dispatcher.forward(request, response);
	}
	
	// insert author
	private void insertAuthor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id_author = request.getParameter("id_author");
		String name_author = request.getParameter("name_author");
		Author newAuthor = new Author(id_author, name_author);
		
		authorDao.insertAuthor(newAuthor);
		response.sendRedirect("list");
	}
	
	// edit author
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		String id_author = request.getParameter("id_author");
		
		Author existingAuthor;
		try {
			existingAuthor = authorDao.selectAuthor(id_author);
			RequestDispatcher dispatcher = request.getRequestDispatcher("author-form.jsp");
			request.setAttribute("author", existingAuthor);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// update author
	private void updateAuthor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id_author = request.getParameter("id_author");
		String name_author = request.getParameter("name_author");
		
		Author author = new Author(id_author, name_author);
		authorDao.updateAuthor(author);
		response.sendRedirect("list");
	}
	
	// delete author
	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id_author = request.getParameter("id_author");
		try {
			authorDao.deleteAuthor(id_author);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}
	
	// default
	private void listAuthor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
			List<Author> listAuthor = authorDao.selectAllAuthor();
			request.setAttribute("listAuthor", listAuthor);;
			RequestDispatcher dispatcher = request.getRequestDispatcher("author-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
