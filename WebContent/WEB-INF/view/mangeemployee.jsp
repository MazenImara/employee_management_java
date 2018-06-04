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
           <h1 Style=' color :blue' ><i>Employee List<b></b></i></h1>
	            <table border="1">
		            <tr>
		                <th>Employee name</th>
		                <th>Employee email</th>
		                <th>Employee password</th>
		                <th>Employee address</th>
		                <th>Employee phone</th>
		                <th>Uppdate</th>
		                <th>Sign To Admin</th>
		                <th>View WorkTimes</th>
		                <th>View TimeOff</th>
		              
		            </tr>
		            <form:form name="mangeEmployee" method="post" action="mangeemployee" modelAttribute="employee">
		                <tr>
		                    <input type='hidden' name="id" value='${employee.id}'/>
		                    <td><input type='text' name="name" value='${employee.name}'/></td>
		                    <td><input type='text' name="email" value='${employee.email}'/></td>
		                    <td><input type='password' name="password" value='${employee.password}'/></td>
		                    <td><input type='text' name="address" value='${employee.address}'/></td>
		                    <td><input type='text' name="phone" value='${employee.phone}'/></td>
		                    <td style="color:blue"><input type="submit" name="update" value="UPDATE"/></td>
		                    <td style="color:blue"><input type="submit" name="signToAdmin"value="Sign To Admin"/></td>
		                    <td style="color:blue"><input type="submit" name="workTime"value="WorkTimes          "/></td>
		                    <td style="color:blue"><input type="submit" name="TimeOff"value="TimeOff          "/></td>
			            </tr>
			        </form:form>
	      </table>
   </div>
</body>
</html>