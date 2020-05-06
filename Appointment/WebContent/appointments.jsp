<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Appointment"%>
<%@ page import="com.util.DBConnect"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<script src="Components/jquery-3.2.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="Components/appo.js"></script>

</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="availableDocs.jsp">Available Doctors</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="appointments.jsp">Appointments</a>
      </li>
      
    </ul>
  </div>
</nav>
<h1>Appointments</h1>
	<div class="container-fluid">
		<form id="booingForm" name="booingForm" method="post"
			action="appointments.jsp">
			<div class="form-group">
				<label for="userId">Patient ID</label> <input type="number"
					class="form-control" id="userId" name="userId" required="required">
			</div>
			<div class="form-group">
				<label for="docId">Doctor ID</label> <input type="number"
					class="form-control" id="docId" name="docId" required="required">
			</div>
			<div class="form-group">
				<label for="docSpec">Doctor Specialization</label> <input
					type="text" class="form-control" name="docSpec" id="docSpec"
					name="docSpec" required="required">
				<!-- <select
					class="form-control" id="docSpec" required="required">

					<% //while(resultSet.next()) {%>
					
					<option value="<%//resultSet.getString(1);%>"></option>
					<%//} %>
				</select> -->

			</div>
			<div class="form-group">
				<label for="hospId">Hospital ID</label> <input type="number"
					class="form-control" id="hospId" name="hospId" required="required">
			</div>
			<div class="form-group">
				<label for="date">date</label> <input type="date"
					class="form-control" id="date" name="date" required="required">
			</div>
			<input class="btn btn-primary" type="button" name="bookingBtn"
				id="bookingBtn" value="Book Now"> <input type="hidden"
				id="hidAppoIdSave" name="hidAppoIdSave" value="">
				<div id="alertSuccess" class="alert alert-success"></div>
				<br>
				<br>
	<div id="alertError" class="alert alert-danger"></div>

	<div class="container-fluid" id = "appoGrid">
		<%
			Appointment appointment = new Appointment();
			out.print(appointment.readAppointments());
		%>
	</div>
	<input type="hidden" id="hidAppoIdSave" name="hidAppoIdSave" value="">
	</form>
	</div>

	
</body>
</html>