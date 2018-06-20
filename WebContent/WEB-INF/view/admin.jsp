<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
<style><%@include file="/WEB-INF/css2/mystyle.css"%></style>
   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <title>Administrator</title>
</head>
<body>


<!-- MOHAMAD Code -->

<div  class="box2 "  align='center'>
    Account: ${log.employee.name } <br>
    <a href="logout"><button>Logout</button></a>
    <a href="loginasemployee"><button>login As Employee</button></a>
</div>
	 <div align="center">
	     <h1 ><i>Projects<b></b></i></h1>
		     <table border="1">
			      <tr class="staticInfoTable">
			          <th>S.No.</th>
			          <th>project title</th>
			          <th>project description</th>
			          <th>project status</th>
			          <th>project timeSpend</th>
			          <th>Select</th>
			          <th>Delete</th>
			          <th>Update</th>
			      </tr>  
		          <c:forEach var="project" items="${projects}" varStatus="status">
			          <form:form name="formUpdProject" method="post" action="updateproject" modelAttribute="project">
					      <tr>
				              <td>${status.index + 1}</td>
				              <input type='hidden' name="id" value='${project.id}'/>
				              <td><input type='text' name="title" value='${project.title}'/></td>
				              <td><input type='text' name="description" value='${project.description}'/></td>
				              <td><input type='hidden' name="status" value='${project.status}'/>${project.status}</td>
				              <td><input type='hidden' name="timeSpend" value='${project.timeSpend}'/>
				                 
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
				              <td ><a href="getproject?id=${project.id}">GO</a></td>
				              <td ><a href="deleteproject?id=${project.id}">DELETE</a></td>	                   
				              <td ><input type="submit" value="UPDATE"/></td>
					      </tr>
		              </form:form>
			      </c:forEach>   

	              <form:form name="formAddProject" method="post" action ="addproject" modelAttribute="project">
	                    <td> </td>
	                    <td> <input type='text' name="title" value='${project.title}'/></td>
	                    <td> <input type='text' name="description" value='${project.description}'/></td>
	                     ${project.status}
	                     ${project.timeSpend}
	                     <td><strike>New</strike></td>
	                     <td><strike>0</strike></td>
	                    <td style="color:blue"><input type="submit" value="ADD Project "/></td>
		           </form:form>            
	            </table>
       </div>
       <div align="center">
           <h1><i>Employee List<b></b></i></h1>
	            <table border="1">
		             <tr class="staticInfoTable">
		                <th>S.No.</th>
		                <th>Employee name</th>
		                <th>Employee email</th>
		                <th>Employee password</th>
		                <th>Employee address</th>
		                <th>Employee phone</th>
		                <th>Select</th>
		                <th>Delete</th>
		                <th>Update</th>
		              
		            </tr>
	                <c:forEach var="employee" items="${employees}" varStatus="status">
	                <form:form name="employeeForm" method="post" action="updateemployee" modelAttribute="employee">
		                <tr>
		                    <td>${status.index + 1}</td>
		                    <input type='hidden' name="id" value='${employee.id}'/>
		                    <td><input type='text' name="name" value='${employee.name}'/></td>
		                    <td><input type='text' name="email" value='${employee.email}'/></td>
		                    <td><input type='password' name="password" value='${employee.password}'/></td>
		                    <td><input type='text' name="address" value='${employee.address}'/></td>
		                    <td><input type='text' name="phone" value='${employee.phone}'/></td>
		                    <td ><a  href="getemployee?id=${employee.id}">GO</a></td>
		                    <td><a  href="deleteemployee?id=${employee.id}">DELETE</a></td>
		                    <td ><input type="submit" value="UPDATE"/></td>
			            </tr>
			        </form:form>
			        </c:forEach>
                    <form:form name="addForm" method="post" action="addemployee" modelAttribute="employee">
	                    <td> </td>
	                    <td> <input type='text' name="name" value='${employee.name}'/></td>
	                    <td> <input type='text' name="email" value='${employee.email}'/></td>
	                    <td> <input type='text' name="password" value='${employee.password}'/></td>
	                    <td> <input type='text' name="address" value='${employee.address}'/></td>
	                    <td> <input type='text' name="phone" value='${employee.phone}'/></td>
	                    <td ><input type="submit" value="ADD Employee     "/></td>            
                    </form:form>       
	            </table>
        </div>
</body>
<!-- End MOHAMAD Code  -->
</html>

