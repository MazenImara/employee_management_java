<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Login</title>
</head>
<body>
        <form:form name="eee" method="post" action="loginasadmin" modelAttribute="employee">
	                     Email:<input type='text' name="email" value='${employee.email}'/><br>
	                     Password:<input type='text' name="password" value='${employee.password}'/><br>
	                     <td style="color:blue"><input type="submit" value="login"/></td> 
        </form:form>
        


            
</body>
</html>