<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Da Venti - Category</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #af6b58">
			<div>
				<a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand"> Da Venti </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/category/list"
					class="nav-link">Categories</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${category != null}">				
					<form action="update" method="post">
				</c:if>
				<c:if test="${category == null}">				
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${category != null}">
            			Edit Category
            		</c:if>
						<c:if test="${category == null}">
            			Add New Category
            		</c:if>
					</h2>
				</caption>

				<c:if test="${category != null}">
					<input type="hidden" name="id_category" value="<c:out value='${category.id_category}' />" />
				</c:if>
				<c:if test="${category == null}">
					<fieldset class="form-group">
						<label>Category ID</label> <input type="text"
							value="<c:out value='${category.id_category}' />" class="form-control"
							name="id_category" required="required">
					</fieldset>
				</c:if>
				<fieldset class="form-group">
					<label>Category Name</label> <input type="text"
						value="<c:out value='${category.name_category}' />" class="form-control"
						name="name_category" required="required">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
