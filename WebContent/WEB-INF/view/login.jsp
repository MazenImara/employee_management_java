<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
<style><%@include file="/WEB-INF/css2/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Login</title>
</head>
<body>
<div   align="center">
		<h1 style="color:#00c7fc">
			<i>login</i>
		</h1>
        <div align="center">
				<form:form name="login" method="post" action="checklogin" modelAttribute="employee">
			        <table class="loginTable">
				        <tr>
				           <td>  Email </td>
				           <td>  <input type='text' name="email" value='${employee.email}'/></td>
				           </tr>
				        <tr>
				            <td>  password </td>
				            <td> <input type='password' name="password" value='${employee.password}'/></td>
				            <td style="color:blue"><input class="btnLogin" type="submit" value="login"/></td> 
				        </tr>
			        </table>
			     </form:form>
        </div>
</div>		        


            
</body>
</html>