<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



                            
<div align="center">
           <h1 Style=' color :blue' ><i><b>Employee List</b></i></h1>
	            <table border="1">
		            <tr>
		                <th>Employee name</th>
		                <th>Employee email</th>
		                <th>Employee password</th>
		                <th>Employee address</th>
		                <th>Employee phone</th>
		                <th>Uppdate</th>
		                <th>Sign To Admin</th>
		            </tr>
		            <form:form name="mangeEmployee" method="post" action="mangeemployee" modelAttribute="employee">
		                <tr>
		                    <input type='hidden' name="id" value='${employee.id}'/>
		                    <td><input type='text' name="name" value='${employee.name}'/></td>
		                    <td><input type='text' name="email" value='${employee.email}'/></td>
		                    <td><input type='password' name="password" value='${employee.password}'/></td>
		                    <td><input type='text' name="address" value='${employee.address}'/></td>
		                    <td><input type='text' name="phone" value='${employee.phone}'/></td>
		                    <td style="color:blue"><input type="submit" name="update" value="UPDATE"/></td>
		                    <td style="color:blue"><input type="submit" name="signToAdmin"value="Sign To Admin"/></td>
			            </tr>
			        </form:form>
	      </table>
	      
	      
	      
</div>
<div align="center">
           <h2 Style=' color :blue' ><i>Time Off <b></b></i></h2>
	            <table border="1">
		            <tr>
		                <th>S.No.</th>
		                <th>From</th>
		                <th>To</th>
		            </tr>
		             <c:forEach var="timeOff" items="${timesOffList}" varStatus="status">
		                <tr>
		                    <td>${status.index + 1}</td>
		                    <td>
			                    <jsp:useBean id="dateObject" class="java.util.Date" />
								<jsp:setProperty name="dateObject" property="time" value="${timeOff.from}" />
							    <b><fmt:formatDate value="${dateObject }" pattern="dd/MM/yyyy--" /></b>
								<b><fmt:formatDate value="${dateObject }" pattern="hh:mm a" /></b>
		                    </td>
		                    <td>
			                    <jsp:useBean id="dateObject2" class="java.util.Date" />
								<jsp:setProperty name="dateObject2" property="time" value="${timeOff.to}" />
								<b><fmt:formatDate value="${dateObject2 }" pattern="dd/MM/yyyy--" /></b>
								<b><fmt:formatDate value="${dateObject2}" pattern="hh:mm a" /></b>
							</td>		                
		                 <tr>   
			          </c:forEach>  
	             </table>
   </div> 
   
   
   <div align="center">
           <h4 Style=' color :blue' ><i>Work Times <b></b></i></h4>
	            <table border="1">
		            <tr>
		                <th>S.No.</th>
		                <th>Date</th>
		                <th>Start Time</th>
		                <th>End Time</th>
		                <th>TimeSpend</th>
		                
		            </tr>
		            <tr>
		                  <c:forEach var="day" items="${days}" varStatus="status">
		                    <td>${status.index + 1}</td>
		                    <td>
		                       <jsp:useBean id="dateObject7" class="java.util.Date" />
					    	   <jsp:setProperty name="dateObject7" property="time" value="${day.date}" />
					           <b><fmt:formatDate value="${dateObject7 }" pattern="dd/MM/yyyy" /></b>
		                    </td>
		                    <td>
			                     <c:choose>
									    <c:when test="${day.start==0}">
									        00:00
									    </c:when>    
									    <c:otherwise>
									       <jsp:useBean id="dateObject3" class="java.util.Date" />
								    	   <jsp:setProperty name="dateObject3" property="time" value="${day.start}" />
								           <b><fmt:formatDate value="${dateObject3 }" pattern="dd/MM/yyyy--" /></b>
									       <b><fmt:formatDate value="${dateObject3 }" pattern="hh:mm a" /></b>
									    </c:otherwise>
						    	</c:choose>
				            </td>
		                    <td>
			                    <c:choose>
									    <c:when test="${day.endTime==0}">
									        00:00
									    </c:when>    
									    <c:otherwise>
									       <jsp:useBean id="dateObject4" class="java.util.Date" />
								    	   <jsp:setProperty name="dateObject4" property="time" value="${day.endTime}" />
								           <b><fmt:formatDate value="${dateObject4 }" pattern="dd/MM/yyyy--" /></b>
									       <b><fmt:formatDate value="${dateObject4 }" pattern="hh:mm a" /></b>
									    </c:otherwise>
						    	</c:choose>
							</td>
							<td>
							  <c:choose>
								    <c:when test="${day.timeSpend<=999}">
								       00:00
								    </c:when> 
								    <c:when test="${day.timeSpend >= 1000 && day.timeSpend <= 3599000 }">
								      <jsp:useBean id="dateObject5" class="java.util.Date" />
							    	  <jsp:setProperty name="dateObject5" property="time" value="${day.timeSpend}" />
								      <b><fmt:formatDate value="${dateObject5 }" pattern=" 00:mm " /></b>
								     
								    </c:when>    
								    <c:otherwise>
					                  <jsp:useBean id="dateObject6" class="java.util.Date" />
							    	  <jsp:setProperty name="dateObject6" property="time" value="${day.timeSpend-3600000}" />
								      <b><fmt:formatDate value="${dateObject6 }" pattern="hh:mm " /></b>
						            </c:otherwise>
						      </c:choose>
							</td>    
		             </c:forEach> 
		             </tr>     
		                   
	             </table>
    </div> 
</body>
</html>


