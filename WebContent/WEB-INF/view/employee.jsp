<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <style><%@include file="/WEB-INF/css/mystyle.css"%></style>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Employee Details</title>
</head>
<body>
<h1>Employee Details</h1> 
    Id: ${employee.id} <br>
    Name: ${employee.name} <br>
    Email: ${employee.email} <br>
    Password: ${employee.password} <br>
    Address: ${employee.address} <br>
    phone: ${employee.phone} <br>
</body>
</html>