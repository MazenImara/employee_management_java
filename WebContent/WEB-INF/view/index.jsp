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
	
	<form method="post" action="addUser">
		Name: <input type="text" name="name"> <br>
		Age: <input type="text" name="age"><br>
		Email: <input type="text" name="email"><br>
		<input type="submit" value="Add"><br>
		</form>
		
		
	<!-- Mohamad Code -->
	
	<h2 class="Employee">Add Employee</h2>
	<form method= "post" action ="addEmployee">
	    Name    <input type="text" name="name"> <br>
	    Email   <input type="text" name="email"> <br>
	    password<input type="password" name="password"> <br>
	    Address <input type="text" name="address"> <br>
	    Phone   <input type="text" name="phone"> <br>
	            <input type="submit" value="Add"><br>
	</form>
	
	
	<c:forEach items="${users}" var="user">
		<a href="user/?id=${user.id }">${user.name }</a>	<br>
	</c:forEach>
		<form method="post" action="getProject">
		Enter ID: <input type="text" name="id">
		<input type="submit" value="Get project"><br>
	    </form>
	    	<h3 style="color:green;">${msg }</h3>
	    	<h4 class="ali" id="myh3">this is h4</h4>
	
	<h5 style="color:green;">${msg2 }</h5>
	
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
   <h8 style="color:green;">${msg3 }</h8>
	
	<!-- End Mohamad -->
	
	
	
	

</body>
</html>