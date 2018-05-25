<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Details</title>
</head>
<body>

<!-- Mohamad -->
	 <div align="center">
		  <h1 Style=' color :blue' ><i>Projects<b></b></i></h1>
		  
	      	<table border="1">
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
          <h1 Style=' color :blue' ><i>the Tasks<b></b></i></h1>
	     
            <table border="1">
		          <tr>
			        <td>   TaskId      </td>
			        <td>   TaskTitle   </td>
			        <td>   Status      </td>
			        <td>   TimeSpend   </td>
			        <td>   Started     </td>
			        <td>   Finish      </td>
			        <td>   EmployeeName</td>
			        <td>   Go          </td>
			        <td>   DEL         </td>
			        <td>   Uppdate     </td>
		          </tr>
		          
		          <c:forEach var="task" items="${project.tasks}" varStatus="status">
			    <!--    <form:form name="formUpdTask" method="post" action="updatetask" modelAttribute="task">-->
			        <tr>
			            <td>   ${task.id } </td>
			            <td>   <input type='text' name="title" value='${task.title}'/></td>
				        <td>   ${task.status } </td>
				        <td>   ${task.timespend } </td>
				        <td>   ${task.started } </td>
				        <td>   ${task.finish } </td>
				        <td>   ${employee.id } </td>
				       
					    <td style="color:blue"><a href="deleteproject?id=${project.id}">DEL</a></td>
					    <td style="color:blue"><input type="submit" value="UPDATE"/></td>	
					    <td style="color:blue"><input type="submit" value="Sign Employee to Task"/></td>

		            </tr>
		           	            
		         
		          </c:forEach> 
		  </table>
		  
      </div>
</body>
</html>

<!-- 


<!-- 
 	      	<div>
 	      				<div class="muu2">
	 	      				<div class="col-sm-3">
								Task Name
							</div>	
							<div class="col-sm-3">
								Status 
							</div>
								TimeSpend
							<div class="col-sm-3">
								Started
							</div>
							<div class="col-sm-3">
								finish
							</div>
							<div class="col-sm-3">
								EmployeeId							
							</div>
						 </div>
					  <div class="muu">
	 					<c:forEach var="task" items="${project.tasks }">	
	
							<div class="col-sm-3">
								${task.title}
							</div>	
							<div class="col-sm-3">
								${task.status}
							</div>
							<div class="col-sm-2">
								${task.timespend}
							</div>
							<div class="col-sm-2">
								${task.started}
							</div>
							<div class="col-sm-2">
								${task.finish}							
							</div>
							<div class="col-sm-2">
								${task.employee_id}							
							</div>
						</c:forEach>
						
					</div>
					
					
					
				</div>
		 
		
		




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
				        <td>   TaskId      </td>
				        <td>   TaskTitle   </td>
				        <td>   Status      </td>
				        <td>   TimeSpend   </td>
				        <td>   Started     </td>
				        <td>   Finish      </td>
				        <td>   EmployeeName</td>
				        <td>   Go          </td>
				        <td>   DEL         </td>
				        <td>   Uppdate     </td>
			          </tr>
			          <c:forEach var="task" items="${tasks}" varStatus="status">
				      <form:form name="formUpdTask" method="post" action="updatetask" modelAttribute="task">
				      <tr>
				            <td>   ${task.id } </td>
				            <td>   <input type='text' name="title" value='${task.title}'/></td>
					        <td>   ${task.status } </td>
					        <td>   ${task.timeSpend } </td>
					        <td>   ${task.started } </td>
					        <td>   ${task.finish } </td>
					        <td>   ${employee.id } </td>
					        <td style="color:blue"><a href="getproject?id=${project.id}">GO</a></td>
						    <td style="color:blue"><a href="deleteproject?id=${project.id}">DEL</a></td>
						    <td style="color:blue"><input type="submit" value="UPDATE"/></td>	
						    <td style="color:blue"><input type="submit" value="Sign Employee to Task"/></td>
			          </tr>
			          </form:form>
			          </c:forEach>  
			        <form:form name="formAddtask" method="post" action ="addtask1" modelAttribute="task">  
			        
		                    <td> </td>
		                    <td> <input type='text' name="title" value='${task.title}'/></td>
		                    <input type='hidden' name="project_id" value='${project.id }'/>
		                     ${task.status}
		                     <td><strike>New</strike></td>
		                     <td><strike>0</strike></td>
		                     <td><strike>0</strike></td>
		                     <td><strike>0</strike></td>
		                     <td><strike>0</strike></td>
		                     <td></td>
		                    <td style="color:blue"><input type="submit" value="ADD Task "/></td>
		            
	                </form:form>
	            </table>
	</div>

</body>

 -->