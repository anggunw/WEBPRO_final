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

import net.daventi.dao.PublisherDAO;
import net.daventi.model.Publisher;

/**
 * Servlet implementation class PublisherServlet
 */
@WebServlet("/Publisher")
public class PublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PublisherDAO publisherDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		publisherDao = new PublisherDAO();
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
			case "/publisher/new":
				showNewForm(request, response);
				break;
			case "/publisher/insert":
				insertPublisher(request, response);
				break;
			case "/publisher/delete":
				deletePublisher(request, response);
				break;
			case "/publisher/edit":
				showEditForm(request, response);
				break;
			case "/publisher/update":
				updatePublisher(request, response);
				break;
			default:
				listPublisher(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("publisher-form.jsp");
		dispatcher.forward(request, response);
	}
	
	// insert publisher
	private void insertPublisher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id_publisher = request.getParameter("id_publisher");
		String name_publisher = request.getParameter("name_publisher");
		String email_publisher = request.getParameter("email_publisher");
		Publisher newPublisher = new Publisher(id_publisher, name_publisher, email_publisher);
		
		publisherDao.insertPublisher(newPublisher);
		response.sendRedirect("list");
	}
	
	// edit publisher
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		String id_publisher = request.getParameter("id_publisher");
		
		Publisher existingPublisher;
		try {
			existingPublisher = publisherDao.selectPublisher(id_publisher);
			RequestDispatcher dispatcher = request.getRequestDispatcher("publisher-form.jsp");
			request.setAttribute("publisher", existingPublisher);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// update publisher
	private void updatePublisher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id_publisher = request.getParameter("id_publisher");
		String name_publisher = request.getParameter("name_publisher");
		String email_publisher = request.getParameter("email_publisher");
		
		Publisher publisher = new Publisher(id_publisher, name_publisher, email_publisher);
		publisherDao.updatePublisher(publisher);
		response.sendRedirect("list");
	}
	
	// delete publisher
	private void deletePublisher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String id_publisher = request.getParameter("id_publisher");
		try {
			publisherDao.deletePublisher(id_publisher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}
	
	// default
	private void listPublisher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
			List<Publisher> listPublisher = publisherDao.selectAllPublisher();
			request.setAttribute("listPublisher", listPublisher);
			RequestDispatcher dispatcher = request.getRequestDispatcher("publisher-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
