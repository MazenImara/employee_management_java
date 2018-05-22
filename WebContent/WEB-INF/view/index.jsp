<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
<style><%@include file="/WEB-INF/css/bootstrap.css"%></style>
<style><%@include file="/WEB-INF/css/jquery.min.js"%></style>
<style><%@include file="/WEB-INF/css/bootstrap.min.js"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
start test :
${project.title }
<c:forEach var="task" items="${project.tasks}" varStatus="status"><br>
	${task.title }
</c:forEach> 
<br>


<br>
endtest  


<!-- ikram project -->

	<input type="button" value="To Projects List" onclick="window.location.href='http://localhost:8080/em/projectsList'">	
	<br>
	<br>
	<form method="post" action="getProject">
		Enter ID: <input type="text" name="id">
		<input type="submit" value="Get project"><br>
	</form>
	
	<h4>Add new project</h4>
	<form method="post" action="addProject">
		Title: <input type="text" name="title"> <br>
		Description: <input type="text" name="description"><br>
		<input type="submit" value="Add project"><br>
	</form>
	
	<h4>Delete project</h4>
	<form method="post" action="deleteProject">
		Enter project id: <input type="text" name="id"> <br>
		<input type="submit" value="Delete project"><br>
	</form>
	

	<h4>Update project</h4>
	<form method="post" action="updateProject">
		Id: <input type="text" name="id"> <br>
		Title: <input type="text" name="title"> <br>
		Description: <input type="text" name="description"><br>
		<input type="submit" value="Update project"><br>
	</form>

	<h4>Add new suggestion</h4>
	<form method="post" action="addSuggestion">
		Project id: <input type="text" name="project_id"> <br>
		Task id: <input type="text" name="task_id"><br>
		Employee id: <input type="text" name="employee_id"><br>
		<input type="submit" value="Add suggestion"><br>
	</form>

	<form method="post" action="getSuggestion">
		Enter ID: <input type="text" name="id">
		<input type="submit" value="Get suggestion by id"><br>
	</form>
	
	
	<input type="button" value="To suggestion List" onclick="window.location.href='http://localhost:8080/em/suggestionList'">
<!-- end ikram -->


	
	
<!-- Gab here -->
	<div class="container">
	  <h2>Button Styles</h2>
	  <button type="button" class="btn">Basic</button>
	  <button type="button" class="btn btn-default">Default</button>
	  <button type="button" class="btn btn-primary">Primary</button>
	  <button type="button" class="btn btn-success">Success</button>
	  <button type="button" class="btn btn-info">Info</button>
	  <button type="button" class="btn btn-warning">Warning</button>
	  <button type="button" class="btn btn-danger">Danger</button>
	  <button type="button" class="btn btn-link">Link</button>      
	</div>
	
<!-- Gab parts end here -->
</body>
</html>