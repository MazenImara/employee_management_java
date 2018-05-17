<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- ikram project -->
	<h4>Project</h4>
	Id: ${project.id }<br>
	Title: ${project.title }<br>
	Description: ${project.description }<br>
	Status: ${project.status }<br>
	Time Spend: ${project.timeSpend }<br>
<!-- end project -->
</body>
</html>