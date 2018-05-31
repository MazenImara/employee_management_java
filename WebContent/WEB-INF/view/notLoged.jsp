<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div   align="center">
	     <h1 Style=' color :blue' ><i>Login Error<b></b></i></h1>
   <table border="1" width="40%" style='background-color:red;font-sise:200 px'>
		<tr>
		 <td><big>you are not log in , to see this page login<br>
		 <br>
		 Try to login again</big> </td>
		</tr>
	</table>
	
	<h1 Style=' color :blue' ><i>login</i></h1>
	<div class="box" align="center">
	<form:form name="login" method="post" action="loginasadmin" modelAttribute="employee">
        <table border="1">
	        <tr>
	           <td>  Email </td>
	           <td>  <input type='text' name="email" value='${employee.email}'/></td>
	           </tr>
	        <tr>
	            <td>  password </td>
	            <td> <input type='password' name="password" value='${employee.password}'/></td>
	            <td style="color:blue"><input type="submit" value="login"/></td> 
	        </tr>
        </table>
     </form:form>
     </div>
</div>		         
</body>
</html>