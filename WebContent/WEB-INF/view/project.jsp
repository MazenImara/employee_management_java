<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mange Project</title>
</head>
<body>

<c:if test="${project.id == 11 }" >
   <b>Test passed!</b>
</c:if>




	
	 <div align="center">
	     <h1 Style=' color:blue' ><i>Project<b></b></i></h1>
		     <table border="1">
			      <tr>
			          <th>project title</th>
			          <th>project description</th>
			          <th>project status</th>
			          <th>project timeSpend</th>
			      </tr>  
			      <tr>
		              <td>${project.title}</td>
		              <td>${project.description}</td>
		              <td>${project.status}</td>
		              <td>project.timeSpend}</td>
			      </tr>
         	</table>
	</div>
	
	<div align="center">
	     <h1 Style=' color :blue' ><i>Tasks<b></b></i></h1>
		     <table border="1">
			      <tr>
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
				              <td><input type='hidden' name="timespend" value='${task.timespend}'/>${task.timespend}</td>
				              <td><input type='hidden' name="started" value='${task.started}'/>${task.started}</td>
				              <td><input type='hidden' name="finish" value='${task.finish}'/>${task.finish}</td>
						       <c:choose>      
						       
			                        <c:when test="${task.employee.id != null}">
			                              <td> ${task.employee.name} </td>
								    </c:when>
								    
								    <c:otherwise>
								          
								               
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