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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${publisher != null}">				
					<form action="update" method="post">
				</c:if>
				<c:if test="${publisher == null}">				
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${publisher != null}">
	            			Edit Publisher
	            		</c:if>
						<c:if test="${publisher == null}">
	            			Add New Publisher
	            		</c:if>
					</h2>
				</caption>

				<c:if test="${publisher != null}">
					<input type="hidden" name="id_publisher" value="<c:out value='${publisher.id_publisher}' />" />
				</c:if>
				<c:if test="${publisher == null}">
					<fieldset class="form-group">
						<label>Publisher ID</label> <input type="text"
							value="<c:out value='${publisher.id_publisher}' />" class="form-control"
							name="id_publisher" required="required">
					</fieldset>
				</c:if>
				<fieldset class="form-group">
					<label>Publisher Name</label> <input type="text"
						value="<c:out value='${publisher.name_publisher}' />" class="form-control"
						name="name_publisher" required="required">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
