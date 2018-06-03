<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
     <div align="center">
		    <h1 Style=' color :blue' ><i>The Task Selected<b></b></i></h1>
		     <table border="1">
			      <tr>
			          <th>S.No.</th>
			          <th> title</th>
			          <th> Status</th>
			          <th> TimeSpend</th>
			          <th> Started</th>
			          <th> Finish</th>
			          <th> Project title</th>
			          <th> Employee.id</th>
			      </tr>    
			      <tr>
		             <td>${status.index + 1}</td>
				                 
				              <td>${task.title}</td>
				              <td>${task.status}</td>
				              <td>${task.timespend}</td>
				              <td>${task.started}</td>
				              <td>${task.finish}</td>
				              <td>${project.title}</td>
				              <td>${task.employee.id}</td>
			      </tr>
		    	</table>
	</div>
	</div>
       <div align="center">
           <h1 Style=' color :blue' ><i>Select Employee<b></b></i></h1>
	            <table border="1">
		            <tr>
		                <th>S.No.</th>
		                <th>Employee name</th>
		                <th>Employee email</th>
		                <th>Employee password</th>
		                <th>Employee address</th>
		                <th>Employee phone</th>
		                
		                <th>sign</th>
		            </tr>
	                <c:forEach var="employee" items="${employees}" varStatus="status">
		                <form:form name="employeesForm" method="Get" action="signemployeetotask" modelAttribute="employee">
			                <tr>
			                    <td>${status.index + 1}</td>
			                     <input type='hidden' name="taskId" value='${task.id}'/>
			                    <input type='hidden' name="employeeId" value='${employee.id}'/>
			                    <td>${employee.name}</td>
			                    <td>${employee.email}</td>
			                    <td>>${employee.password}</td>
			                    <td>${employee.address}</td>
			                    <td>${employee.phone}</td>
			                    <input type='hidden' name="projectId" value='${project.id}'/>
			                    <td style="color:blue"><input type="submit" value="Select"/></td>
				            </tr>
				        </form:form>
			        </c:forEach>
                         
	            </table>
        </div>
		<div align="center">
           <h1 Style=' color :blue' ><i>The Suggestion<b></b></i></h1>
	            <table border="1">
		            <tr>
		                <th>S.No.</th>
		                <th>Employee name</th>
		                <th>Project name</th>
		                <th>Task Name</th>
		            </tr>
	                <c:forEach var="suggestion" items="${suggestions}" varStatus="status">
			                <tr>
			                    <td>${status.index + 1}</td>
			                    <c:forEach var="employee" items="${employees}" varStatus="status">
				                    <c:if test="${ suggestion.employee_id == employee.id}" >
				                    <td>${employee.name}</td>
				                    </c:if>
				                </c:forEach>
			                    <c:forEach var="project" items="${projects}" varStatus="status">
			                        <c:if test="${ suggestion.project_id == project.id}" >
				                    <td>${project.title}</td>
				                    </c:if>
				                </c:forEach>   
			                    <c:forEach var="task" items="${tasks}" varStatus="status">
			                        <c:if test="${ suggestion.task_id == task.id}" >
				                    <td>${task.title}</td>
				                    </c:if>
					            </c:forEach>
				            </tr>
			        </c:forEach>
                         
	            </table>
        </div>	     
</body>
</html>