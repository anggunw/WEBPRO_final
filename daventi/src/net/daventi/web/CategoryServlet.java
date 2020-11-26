package net.daventi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daventi.dao.CategoryDAO;
import net.daventi.model.Category;

@WebServlet("/")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
	
	public void init() {
		categoryDAO = new CategoryDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/category/new":
				showNewForm(request, response);
				break;
			case "/category/insert":
				insertCategory(request, response);
				break;
			case "/category/delete":
				deleteCategory(request, response);
				break;
			case "/category/edit":
				showEditForm(request, response);
				break;
			case "/category/update":
				updateCategory(request, response);
				break;
			default:
				listCategory(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Category> listCategory = categoryDAO.selectAllCategory();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("category-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id_category = request.getParameter("id_category");
		
		Category existingCategory = categoryDAO.selectCategory(id_category);
		RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
		request.setAttribute("category", existingCategory);
		dispatcher.forward(request, response);

	}

	private void insertCategory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String id_category = request.getParameter("id_category");
		String name_category = request.getParameter("name_category");
		Category newCategory = new Category(id_category, name_category);
		categoryDAO.insertCategory(newCategory);
		response.sendRedirect("list");
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String id_category = request.getParameter("id_category");
		String name_category = request.getParameter("name_category");

		Category category = new Category(id_category, name_category);
		categoryDAO.updateCategory(category);
		response.sendRedirect("list");
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String id_category = request.getParameter("id_category");
		categoryDAO.deleteCategory(id_category);
		response.sendRedirect("list");

	}

}