<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/bootstrap.css"%></style>
<style><%@include file="/WEB-INF/css/jquery.min.js"%></style>
<style><%@include file="/WEB-INF/css/bootstrap.min.js"%></style>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>

<style><%@include file="/WEB-INF/css/TestingNewTable.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<!-- Mohamad -->

<h1>Employee Details</h1> 
    Id: ${employee.id} <br>
    Name: ${employee.name} <br>
    Email: ${employee.email} <br>
    Password: ${employee.password} <br>
    Address: ${employee.address} <br>
    phone: ${employee.phone} <br>

<!-- end Mohamad -->
</body>
</html>