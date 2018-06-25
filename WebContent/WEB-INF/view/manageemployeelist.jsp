<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
<style><%@include file="/WEB-INF/css2/mystyle.css"%></style>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <title>Administrator</title>
</head>
<body>


<!-- MOHAMAD Code -->
<jsp:include  flush="true" page="/WEB-INF/view/header.jsp"/>
	 
       <div align="center">
           <h1><i>Employee List<b></b></i></h1>
	            <table border="1">
		             <tr class="staticInfoTable">
		                <th>S.No.</th>
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
	                <form:form name="employeeForm" method="post" action="updateemployee" modelAttribute="employee">
		                <tr>
		                    <td>${status.index + 1}</td>
		                    <input type='hidden' name="id" value='${employee.id}'/>
		                    <td><input type='text' name="name" value='${employee.name}'required/></td>
		                    <td><input type='text' name="email" value='${employee.email}'required/></td>
		                    <td><input type='password' name="password" value='${employee.password}' required/></td>
		                    <td><input type='text' name="address" value='${employee.address}' required/></td>
		                    <td><input type='text' name="phone" value='${employee.phone}' required/></td>
		                    <td ><a  href="getemployee?id=${employee.id}">GO</a></td>
		                    <td><a  href="deleteemployee?id=${employee.id}">DELETE</a></td>
		                    <td ><input type="submit" value="UPDATE"/></td>
			            </tr>
			        </form:form>
			        </c:forEach>
                    <form:form name="addForm" action="addemployee"  method="post" modelAttribute="employee" >
                        <td></td>
	                    <td> <input type='text' name="name" value='${employee.name}' required/></td>
	                    <td> <input type='text' name="email" value='${employee.email}' required/></td>
	                    <td> <input type='text' name="password" value='${employee.password}' required/></td>
	                    <td> <input type='text' name="address" value='${employee.address}' required/></td>
	                    <td> <input type='text' name="phone" value='${employee.phone}' required/></td>
	                    <td ><input type="submit" value="ADD Employee     "/></td>            
                    </form:form>       
	            </table>
        </div>
</body>
<!-- End MOHAMAD Code  -->
</html>

