<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>test</h1>
	
	
	
	
	
	
	
			          <form:form name="formUpdProject" method="post" action="addtask1" modelAttribute="task">
				              <input type='text' name="title" /> 
				              <input type='text' name="project.id" value="${project.id }" /> 
				             <input type="submit" value="UPDATE"/>
					      </tr>
		              </form:form>	
	
</body>
</html>