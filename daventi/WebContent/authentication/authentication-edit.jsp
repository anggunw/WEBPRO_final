<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Da Venti - Edit Profile</title>
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
				<li><a href="<%=request.getContextPath()%>/author"
					class="nav-link">Author</a></li>
				<li><a href="<%=request.getContextPath()%>/publisher"
					class="nav-link">Publisher</a></li>
				<li><a href="<%=request.getContextPath()%>/status"
					class="nav-link">Status</a></li>
				<li><a href="<%=request.getContextPath()%>/method"
					class="nav-link">Payment Method</a></li>
				<li><a href="<%=request.getContextPath()%>/logout"
						class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>

    <div class="row">
        <div class="col-lg-12">
            <form action="/dashboard/{{auth()->user()->id}}/update" method="post">
                <div class="form-group">
                    <label for="ProfileName">Name</label>
                    <input name="name_employee" type="text" class="form-control" id="InputName" value='${employee.name_employee}' required>
                </div> 
                <div class="form-group">
                    <label for="ProfileEmail">Email</label>
                    <input name="email_employee" type="text" class="form-control" id="InputEmail" placeholder="Author" value='${employee.email_employee}' required>
                </div>
                <div class="form-group">
                    <label for="ProfilePhone">Phone Number</label>
                    <input name="phone_num_employee" type="number" class="form-control" id="InputPhone" placeholder="Price" value='${employee.phone_num_employee}' required>
                </div>
                <button type="button" class="btn btn-secondary" onclick="document.location.href='/dashboard';">Cancel</button>
                <button type="submit" class="btn btn-warning">Update</button>
            </form>
        </div>
    </div>
</body>
</html>