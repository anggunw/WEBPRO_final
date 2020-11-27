<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Da Venti - Publisher</title>
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
				<li><a href="<%=request.getContextPath()%>/category"
					class="nav-link">Categories</a></li>
				<li><a href="<%=request.getContextPath()%>/author/list"
					class="nav-link">Author</a></li>
				<li><a href="<%=request.getContextPath()%>/publisher/list"
					class="nav-link">Publisher</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Publisher</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/publisher/new" class="btn btn-success">Add
					New Publisher</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="publisher" items="${lisPublisher}">
						<tr>
							<td><c:out value="${publisher.id_publisher}" /></td>
							<td><c:out value="${publisher.name_publisher}" /></td>
							<td><c:out value="${publisher.email_publisher}" /></td>
							<td><a class="btn btn-warning" href="edit?id_publisher=<c:out value='${publisher.id_publisher}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a class="btn btn-danger" href="delete?id_publisher=<c:out value='${publisher.id_publisher}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
