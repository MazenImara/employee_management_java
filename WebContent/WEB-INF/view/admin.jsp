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
<jsp:include  flush="true" page="/WEB-INF/view/header.jsp"/>


	 <div align="center">
	     <h1 ><i>Projects<b></b></i></h1>
		     <table border="1">
			      <tr class="staticInfoTable">
			          <th>S.No.</th>
			          <th>project title</th>
			          <th>project description</th>
			          <th>project status</th>
			          <th>project timeSpend</th>
			         
			      </tr>  
		          <c:forEach var="project" items="${projects}" varStatus="status">
					      <tr>
				              <td>${status.index + 1}</td>
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
			      </c:forEach>   
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
		            </tr>
	                <c:forEach var="employee" items="${employees}" varStatus="status">
		                <tr>
		                    <td>${status.index + 1}</td>
		                    <td>${employee.name}</td>
		                    <td>${employee.email}</td>
		                    <td>******</td>
		                    <td>${employee.address}</td>
		                    <td>${employee.phone}</td>
		                   
			            </tr>
			        </c:forEach>
                      
	            </table>
        </div>
</body>
<!-- End MOHAMAD Code  -->
</html>

