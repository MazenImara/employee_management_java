<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
    <h1>Suggestion List</h1>
		<table border="1">
    		<tr>
        		<th>Title</th>
        		<th>Description</th>
        		<th>Status</th>
                <th>TimeSpend</th>
                <th>Show</th>
                <th>Delete</th>
			</tr>  
 
 
 		<c:forEach var="project" items="${getProjects }">
 	       <td>${project.title}</td>
 				<c:forEach var="task" items="${getTasks }">
	 				<td>${task.title}</td>
	 				<td>${task.status}</td>
	 				<td>Start</td>
	 				<td>Pause</td>
	 				<td>Finish</td>
 				</c:forEach>
 
 		</c:forEach>
 
      <!--<c:forEach var="suggestion" items="${getSuggestions}" >
            <tr>
                <td>${suggestion.id }</td>
                <td>${project.title}</td>
                <td>${project.description}</td>
                <td>${project.status}</td>
                <td>${project.timeSpend}</td>
                <td><a style="color:seashell" href="getSuggestion?id=${suggestion.id}">show</a> </td>
                <td><a style="color:seashell" href="deleteSuggestion?id=${Suggestion.id}">delete</a> </td>
                
			</tr>
			</c:forEach>      -->	       
		</table>	
</div>
</body>
</html>