<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/bootstrap.css"%></style>
<style><%@include file="/WEB-INF/css/jquery.min.js"%></style>
<style><%@include file="/WEB-INF/css/bootstrap.min.js"%></style>
<style><%@include file="/WEB-INF/css2/mystyle.css"%></style>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <div  class="box2 "  align='center'>
        Account: ${log.employee.name } <br>
        <a href="logout"><button>Logout</button></a><br>

    </div>

          
        <ul  class="nav navbar-nav menuBar">
            <li>
                
	            <input type="button" value="Employee Menu" onclick="window.location.href='http://localhost:8080/em/employee'">
	           

    
            </li>

            <li>
                <a href="managetimeoff?employeeId=${log.employee.id}">Manage your TimeOff</a>
            </li>

            <li>		
                <a href="allsuggestions">Suggestions List</a>
            </li>
            <li>                
                <c:if test='${  log.role == "AdminAsEmployee"}'>
                    <a href="backToAdmin"><button>Back  to admin minue</button></a>
	            </c:if>
    
            </li>

        </ul>
      
</body>
</html>


