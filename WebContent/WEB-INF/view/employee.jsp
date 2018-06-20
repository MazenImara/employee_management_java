<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/bootstrap.css"%></style>
<style><%@include file="/WEB-INF/css/jquery.min.js"%></style>
<style><%@include file="/WEB-INF/css/bootstrap.min.js"%></style>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>

<style><%@include file="/WEB-INF/css/TestingNewTable.css"%></style>

<style><%@include file="/WEB-INF/css2/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- MOHAMAD Gab code -->
<div  class="box2 "  align='center'>
   Account: ${log.employee.name } <br>
   <a href="logout"><button>Logout</button></a>
</div>
 <ul  class="nav navbar-nav menuBar">
	<li>
		<c:if test="${ loginasadmin == false}" >
	      <a href='employee'>Employee List</a>
		</c:if>
	</li>
	<li>
    	<a href="managetimeoff?employeeId=${log.employee.id}">Manage your TimeOff</a>
	</li>
	<li>		
		<a href="allsuggestions">Suggestions List</a>
	</li>
 </ul>
 
<!-- MOHAMAD End -->

<!-- Gab -->

<div class="col-sm-12 taskDesign">
	<div class="row">
		<div>
			<h1  align="center">Employee List</h1>
		</div>
		<c:forEach var="project" items="${getProjects }">
			<div class="projectInfo">
 	      	Project name: ${project.title}
 	      	</div>
 	      			<div >
 	      				<div class="staticInfo">
	 	      				<div class="col-sm-3">
								Task Name
							</div>	
								
							<div class="col-sm-3">
								Status Of Task
							</div>
								
							<div class="col-sm-6">
								Start/Pause/Finish	
							</div>
							
						</div>
						<div>
							<c:forEach var="task" items="${project.tasks }">	
		
								<div class="col-sm-3">
									${task.title}
								</div>	
									
								<div class="col-sm-3">
									${task.status}
								</div>
									
								<div class="col-sm-6">
									<c:if test="${task.status == 'New' || task.status == 'Paused' || task.status == 'Finished'}">
										<a href="start?id=${task.id }"><button>Start</button></a>
									</c:if>	
									<c:if test="${task.status == 'Started'}">
										<a href="pause?id=${task.id }"><button>Pause</button></a>
										<a href="finish?id=${task.id }"><button>Finish</button></a>
									</c:if>


								</div>								
																
							</c:forEach>
						
						</div>
					</div>
			</c:forEach>
		</div>
		<br>
		
</div>

<!-- Gab endline -->

</body>
</html>