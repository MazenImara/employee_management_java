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
<title>Hello</title>
</head>
<body>

<form method="post" action="getemployee">
		Enter ID: <input type="text" name="id">
		<input type="submit" value="getemployee"> <br>
	</form>
	
	<h4>Add employee</h4>
	<form method="post" action="addemployee">
		Name:  <input type="text" name="name"> <br>
		Email: <input type="text" name="email"><br>
		Password:<input type="text" name="password"><br>
		Address: <input type="text" name="address"><br>
		Phone:  <input type="text" name="phone"><br>
		<input type="submit" value="Add Employee"><br> 
	</form>
		        
                  

start test :
<!--  
${project.title }
<c:forEach var="task" items="${project.tasks}" varStatus="status"><br>
	${task.title }
</c:forEach> 
<br>

<c:if test="${logedEmployee != null}">
	hello  ${logedEmployee.name }
</c:if>




-->
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
	    	<h3 style="color:green;">${msg }</h3>
	    	<h4 class="ali" id="myh3">this is h4</h4>
	

	
	<h6 class="Employee">update Employee</h6>
	 <form method= "post" action ="updateemployee">
	    id    <input type="text" name="id"> <br>
	    Name    <input type="text" name="name"> <br>
	    Email   <input type="text" name="email"> <br>
	    password<input type="password" name="password"> <br>
	    Address <input type="text" name="address"> <br>
	    Phone   <input type="text" name="phone"> <br>
	            <input type="submit" value="update"><br>
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