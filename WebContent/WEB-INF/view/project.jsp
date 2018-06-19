<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mange Project</title>
</head>
<body>
	
	<input type="button" value="To Admin Menu" onclick="window.location.href='http://localhost:8080/em/admin'">

	 <div align="center">
	     <h1 Style=' color:blue' ><i>Project<b></b></i></h1>
		     <table border="1">
			       <tr style= "background:yellow">
			          <th>project title</th>
			          <th>project description</th>
			          <th>project status</th>
			          <th>project timeSpend</th>
			      </tr>  
			      <tr>
		              <td>${project.title}</td>
		              <td>${project.description}</td>
		              <td>${project.status}</td>
		              <td>
		                  <c:choose>
							    <c:when test="${project.timeSpend <= 999}">
							       00:00
							    </c:when> 
							    <c:when test="${project.timeSpend >= 1000 && project.timeSpend <= 3599000 }">
							      <jsp:useBean id="dateObject10" class="java.util.Date" />
						    	  <jsp:setProperty name="dateObject10" property="time" value="${project.timeSpend}" />
							      <b><fmt:formatDate value="${dateObject10 }" pattern=" 00:mm " /></b>
							     
							    </c:when>    
							    <c:otherwise>
				                  <jsp:useBean id="dateObject11" class="java.util.Date" />
						    	  <jsp:setProperty name="dateObject11" property="time" value="${project.timeSpend-3600000}" />
							      <b><fmt:formatDate value="${dateObject11 }" pattern="hh:mm " /></b>
					            </c:otherwise>
			         	 </c:choose>
		              </td>
			      </tr>
         	</table>
	</div>
	
	<div align="center">
	     <h1 Style=' color :blue' ><i>Tasks<b></b></i></h1>
		     <table border="1">
			       <tr style= "background:yellow">
			          <th>S.No.</th>
			          <th> title</th>
			          <th> Status</th>
			          <th> TimeSpend</th>
			          <th> Started</th>
			          <th> finish</th>
			          <th> employee Name</th>
			          <th>Delete</th>
			          <th>Update</th>
			      </tr>  
			   
		          <c:forEach var="task" items="${project.tasks}" varStatus="status">
			         
			          <form:form name="formDel&UpdateTask" method="post" action="del&updatetask" modelAttribute="task">
					      <tr>                                            
				              <td>${status.index + 1}</td>
				                  <input type='hidden' name="id" value='${task.id}'/>
				              <td><input type='text' name="title" value='${task.title} '/></td>
				              <td><input type='hidden' name="status" value='${task.status}'/>${task.status}</td>
				              <td><input type='hidden' name="timespend" value='${task.timespend}'/>
				                    <c:choose>
									    <c:when test="${task.timespend<=999}">
									       00:00
									    </c:when> 
									    <c:when test="${task.timespend >= 1000 && task.timespend <= 3599000 }">
									      <jsp:useBean id="dateObject1" class="java.util.Date" />
								    	  <jsp:setProperty name="dateObject1" property="time" value="${task.timespend}" />
									      <b><fmt:formatDate value="${dateObject1 }" pattern=" 00:mm " /></b>
									     
									    </c:when>    
									    <c:otherwise>
						                  <jsp:useBean id="dateObject2" class="java.util.Date" />
								    	  <jsp:setProperty name="dateObject2" property="time" value="${task.timespend-3600000}" />
									      <b><fmt:formatDate value="${dateObject2 }" pattern="hh:mm " /></b>
							            </c:otherwise>
									 </c:choose>
				              </td>
				              <td><input type='hidden' name="started" value='${task.started}'/>
				                  <c:choose>
									    <c:when test="${task.started==0}">
									        00:00
									    </c:when>    
									    <c:otherwise>
									       <jsp:useBean id="dateObject3" class="java.util.Date" />
								    	   <jsp:setProperty name="dateObject3" property="time" value="${task.started}" />
								           <b><fmt:formatDate value="${dateObject3 }" pattern="dd/MM/yyyy--" /></b>
									       <b><fmt:formatDate value="${dateObject3 }" pattern="hh:mm a" /></b>
									    </c:otherwise>
									</c:choose>
				                  </td>
				              <td><input type='hidden' name="finish" value='${task.finish}'/>
				                  <c:choose>
				                        <c:when test="${task.finish==0}">
									        00:00
									    </c:when>    
									    <c:otherwise>
						                   <jsp:useBean id="dateObject4" class="java.util.Date" />
								    	   <jsp:setProperty name="dateObject3" property="time" value="${task.finish}" />
								           <b><fmt:formatDate value="${dateObject4 }" pattern="dd/MM/yyyy--" /></b>
									       <b><fmt:formatDate value="${dateObject4 }" pattern="hh:mm a" /></b>
							            </c:otherwise>
								  </c:choose>
				                  </td>
						       <c:choose> 
			                        <c:when test="${task.employee.id != null}">
			                              <td> ${task.employee.name} </td>
								    </c:when>
								    <c:when test="${suggestions == null}">
			                                <td style="color:blue"><a  href="makesuggestion?taskId=${task.id}&projectId=${project.id}">Make Suggestion</a></td>										             
								    </c:when>
								    <c:otherwise>
							              <c:set var="check" value="false"/>
							              <c:forEach var="suggestion" items="${suggestions}" varStatus="status">
							                  <c:forEach var="employee" items="${employees}" varStatus="status">
										         <c:if test="${suggestion.task_id == task.id && suggestion.employee_id == employee.id}" >
										             <c:set var="check" value="${true}"/>     
										             <td>suggested to ${employee.name} </td>
										         </c:if>
										      </c:forEach>   
										  </c:forEach>
										         <c:if test="${suggestion.task_id != task.id && check==false}" >     
										             <td style="color:blue"><a  href="makesuggestion?taskId=${task.id}&projectId=${project.id}">Make Suggestion</a></td>
										         </c:if>
							        </c:otherwise>
					           </c:choose>  
				              <input type='hidden' name="project.id" value='${project.id}'/>
				              <td style="color:blue"><input type="submit"   name="delete"value="DELETE"></td>	
				              <td style="color:blue"><input type="submit"  name="update" value="UPDATE"/></td> 
				          </tr>
				        </form:form>  
				      </c:forEach> 
		          <form:form name="formadd Task" method="post" action="addnewtask" modelAttribute="task">
				              <tr>
				                   <td></td>
					              <td> <input type='text' name="title" value='${task.title}'/></td> 
					                   <input type='hidden' name="project.id" value="${project.id }"/>
					              <td><strike>New</strike></td>
		                          <td><strike>0</strike></td>
		                          <td><strike>0</strike></td>
		                          <td><strike>0</strike></td>
		                          <td><strike>0</strike></td>
		                          <td style="color:blue"><input type="submit" value="ADD Task "/></td>
		                      </tr>
	                  
                    </form:form>
	          </table>
	    </div>
	                    
	
</body>
</html>