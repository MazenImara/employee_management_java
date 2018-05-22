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
<!-- ikram project -->
	<div align="center">
    <h1>Projects</h1>
		<table border="1">
    		<tr>
    			<th>#</th>
    			<th>Id</th>
        		<th>Title</th>
        		<th>Description</th>
        		<th>Status</th>
                <th>TimeSpend</th>
                <th>Show</th>
                <th>Delete</th>
			</tr>  
 
            <c:forEach var="project" items="${getProjects}" varStatus="status">
            <tr>
                <td>${status.index}</td>
                <td>${project.id }</td>
                <td>${project.title}</td>
                <td>${project.description}</td>
                <td>${project.status}</td>
                <td>${project.timeSpend}</td>
                <td><a style="color:red" href="getProject?id=${project.id}">show</a> </td>
                <td><a style="color:red" href="deleteProject?id=${project.id}">delete</a> </td>                
			</tr>
			<c:forEach var="task" items="${getTasks }" varStatus="status">
			<tr>
                <td>${status.index}</td>
                <td>${task.id }</td>
                <td>${task.title}</td>
                <td>${task.status}</td>
                <td>Start</td>
                <td>Pause</td>
                <td>Ended</td>
                <td><a style="color:red" href="getTask?id=${task.id}">show</a> </td>
                <td><a style="color:red" href="deletetask?id=${task.id}">delete</a> </td>                
			</tr>
			</c:forEach>
			</c:forEach>             
		</table>
	</div>
<div align="center">
    <h1>Task</h1>
		<table border="1">
	
	</table>
</div>
		<input type="button" value="To Index" onclick="window.location.href='http://localhost:8080/em'">	
<!-- end project -->
</body>
</html>