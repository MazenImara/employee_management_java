<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <title>Insert title here</title>
</head>
<body>

        <div align="center">
            <h1 Style=' color :blue' ><i>Administrator Page<b></b></i></h1>
            <table border="1">
            <tr>
                <th>S.No.</th>
                <th>project title</th>
                <th>project description</th>
                <th>project status</th>
                <th>project timeSpend</th>
                
                 <th>Update</th>
                
              </tr>  
                
                <c:forEach var="project" items="${project}" varStatus="status">
                <form:form name="productForm" method="post" action="updateproject" modelAttribute="project">
                <tr>
                	
	                    <td>${status.index + 1}</td>
	                    <td><input type='hidden' name="id" value='${project.id}'></td>
	                    <td><input type='text' name="name" value='${project.title}'></td>
	                    <td><input type='text' name="email" value='${project.description}'></td>
	                    <td><input type='text' name="password" value='${project.status}'></td>
	                    <td><input type='text' name="address" value='${project.timeSpend}'></td>
	                    <td style="color:blue"><a  href="getProject?id=${employee.id}" onclick='go'>Go</a></td>
	                    <td style="color:blue"><a  href="deleteProject?id=${employee.id}">Del</a></td>
	                    <td style="color:blue"><input type="submit" value="up date"></td>
                </tr>
                
                    </form:form>
                </c:forEach>               
            </table>
        </div>


</body>
</html>