<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project</title>
</head>
<body>
<!-- ikram project -->
	
		
<!-- end project -->

<div align="center">
	     <h1 Style=' color :blue' ><i>Projects<b></b></i></h1>
		    <table border="2">
		        <tr>
			        <td>   Title </td>
			        <td>   Description </td>
			        <td>   Status </td>
			        <td>   TmieSpend </td>
		        </tr>
		        <tr>
		            <td>   ${project.title } </td>
			        <td>   ${project.description } </td>
			        <td>   ${project.status }</td>
			        <td>   ${project.timeSpend } </td>
		        </tr>
		     </table>
</div>
<div align="center">
	     <h2 Style=' color :blue' ><i>TheTasks<b></b></i></h2>
		    <table border="1">
		        <tr>
			        <td>   TaskId</td>
			        <td>   TaskTitle </td>
			        <td>   Description </td>
			        <td>   Status </td>
			        <td>   TimeSpend </td>
			        <td>   EmployeeName </td>
			        <td>   Go </td>
			        <td>   DEL </td>
			        <td>   Uppdate </td>
		        </tr>
		        <tr>
		        <!--  
		            <c:forEach var="task" items="${tasks}" varStatus="status">
			        <form:form name="formUpdTask" method="post" action="updatetask" modelAttribute="task">
			        -->
			            <td>   ${task.id } </td>
			            <td><input type='text' name="title" value='${task.title}'/></td>
			            <td> <input type='text' name="description" value='${task.description}'/></td>
				        <td>   ${task.status } </td>
				        <td>   ${task.timeSpend } </td>
				        <td>   ${employee.id } </td>
				        <td style="color:blue"><a href="getproject?id=${project.id}">GO</a></td>
					    <td style="color:blue"><a href="deleteproject?id=${project.id}">DEL</a></td>
					    <td style="color:blue"><input type="submit" value="UPDATE"/></td>	
					    <td style="color:blue"><input type="submit" value="Sign Employee to Task"/></td>
		        </tr>
		        <tr>
		        <!-- <form:form name="formAddProject" method="post" action ="addtask" modelAttribute="project">  -->
		       
	                    <td> </td>
	                    <td> <input type='text' name="title" value='${task.title}'/></td>
	                    <td> <input type='text' name="description" value='${task.description}'/></td>
	                   <!--  
	                     ${task.status}
	                     ${task.timeSpend}
	                     -->
	                     <td><strike>New</strike></td>
	                     <td><strike>0</strike></td>
	                     <td></td>
	                    <td style="color:blue"><input type="submit" value="ADD Task "/></td>
                </tr>
       </table>
  </div>

</body>
</html>