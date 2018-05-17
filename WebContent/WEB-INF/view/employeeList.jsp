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
            <h1>Employee List</h1>
            <table border="1">
            <tr>
                <th>S.No.</th>
                <th>Employee id</th>
                <th>Employee name</th>
                <th>Employee email</th>
                <th>Employee password</th>
                <th>Employee address</th>
                <th>Employee phone</th>
                <th>Select</th>
                <th>Delete</th>
                 <th>Update</th>
                
              </tr>  
                
                <c:forEach var="employee" items="${employees}" varStatus="status">
                <form:form name="productForm" method="post" action="updateemployee" modelAttribute="employee">
                <tr>
                	
	                    <td>${status.index + 1}</td>
	                    <td><input type='hidden' name="id" value='${employee.id}'></td>
	                    <td><input type='text' name="name" value='${employee.name}'></td>
	                    <td><input type='text' name="email" value='${employee.email}'></td>
	                    <td><input type='text' name="password" value='${employee.password}'></td>
	                    <td><input type='text' name="address" value='${employee.address}'></td>
	                    <td><input type='text' name="phone" value='${employee.phone}'></td>
	                    <td style="color:blue"><a  href="getEmployee?id=${employee.id}">Go</a></td>
	                    <td style="color:blue"><a  href="deleteEmployee?id=${employee.id}">Del</a></td>
	                    <td style="color:blue"><input type="submit" value="up date"></td>
                </tr>
                
                    </form:form>
                </c:forEach>               
            </table>
        </div>


</body>
</html>