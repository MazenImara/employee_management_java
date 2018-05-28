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
 
			       <h1 Style=' color :blue' ><i>Are you Sure you need delete this task<b></b></i></h1>
					        <form:form name="formDelTask" method="post" action="deletetask1" modelAttribute="task">
				                  <input type='hidden' name="id" value='${task.id}'/>
				              <input type='hidden' name="title" value='${task.title}'/>${task.title}
				              <input type='hidden' name="status" value='${task.status}'/>
				              <input type='hidden' name="timespend" value='${task.timespend}'/>
				              <input type='hidden' name="started" value='${task.started}'/>
				              <input type='hidden' name="finish" value='${task.finish}'/>
				              <input type='hidden' name="employee_id" value='${task.employee_id}'/>
				              ${project.id}
				             
				              <input type="submit" value="YES">
		              </form:form>
				              
					    
		             
</body>
</html>