<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h1 class="ali">test from index</h1>
	
	<form method="post" action="adduser">
		Name: <input type="text" name="name"> <br>
		Age: <input type="text" name="age"><br>
		Email: <input type="text" name="email"><br>
		<input type="submit" value="Add"><br>
	</form>
	
	<h3 class="ali" id="myh3">this is h3</h3>
	
	<c:forEach items="${users}" var="user">
		<a href="user/?id=${user.id }">${user.name }</a>	<br>
	</c:forEach>
	
	
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
		Status: <input type="text" name="status"><br>
		Time Spend: <input type="text" name="timeSpend"><br>
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
		Status: <input type="text" name="status"><br>
		Time Spend: <input type="text" name="timeSpend"><br>
		<input type="submit" value="Update project"><br>
	</form>

<!-- end ikram -->

</body>
</html>