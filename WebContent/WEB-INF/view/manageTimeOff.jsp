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
<title>Insert title here</title>
</head>
<body>
<!--MOHAMAD Code  -->

<div  class="box "  align='center'>
    Account: ${log.employee.name } <br>
    <a href="logout"><button>Logout</button></a><br>
    <c:if test="${ loginasadmin == false}" >
	     <input type="button" value="To Employee Menu" onclick="window.location.href='http://localhost:8080/em/employee'">
	</c:if>
	<c:if test="${ loginasadmin == true}" >
	     <input type="button" value="To Employee Menu" onclick="window.location.href='http://localhost:8080/em/loginasemployee'">
	</c:if>
    
</div>


   <div align="center">
	     <h1 Style=' color :blue' ><i>Your TimeOff <b></b></i></h1>
		     <table border="1">
			      <tr style= "background:yellow">
			          <th>S.No.</th>
			          <th>From</th>
			          <th>To</th>
			          <th>DELETE</th>
			      </tr> 
			     
	                  <c:forEach var="timeOff" items="${timesOff}" varStatus="status">
	                    <form:form name="formDeleteTimeOff" method="post" action="deletetimeoff" modelAttribute="timeoff">
	                     <tr> 
	                     <td>${status.index + 1}</td>
	                       <input type='hidden' name="id" value='${timeOff.id}'/>
	                       <input type='hidden' name="from" value='${timeOff.from}'/>
	                     <td>  <jsp:useBean id="dateObject" class="java.util.Date" />
						       <jsp:setProperty name="dateObject" property="time" value="${timeOff.from}" />
							   <b><fmt:formatDate value="${dateObject }" pattern="dd/MM/yyyy--" /></b>
							   <b><fmt:formatDate value="${dateObject }" pattern="hh:mm a" /></b>
	                     </td>
				           <input type='hidden' name="to" value='${timeOff.to}'/>
					           <td><jsp:useBean id="dateObject2" class="java.util.Date" />
						   	   <jsp:setProperty name="dateObject2" property="time" value="${timeOff.to}" />
							   <b><fmt:formatDate value="${dateObject2 }" pattern="dd/MM/yyyy--" /></b>
						 	   <b><fmt:formatDate value="${dateObject2}" pattern="hh:mm a" /></b>
				    	   <input type='hidden' name="employee_id" value='${timeOff.employee_id}'/>
						   <td style="color:blue"><input type="submit" value="DELETE   "/></td>
	                     </tr> 
	                      </form:form>
	                      </c:forEach>
	                     <tr>
		                      <form:form name="formaddtimeoff" method="post" action="addtimeoff" modelAttribute="timeOff">
		                          <td>${status.index + 2}</td>
			                      <input type='hidden' name="employeeId" value='${log.employee.id}'/>
			                      <td><input type='datetime-local'   name = "date1" value='${date1}'/></td>
			                      <td><input type='datetime-local'   name = "date2" value='${date2}'/></td>
			                      <td style="color:blue"><input type="submit" value="   ADD      "/></td> 
		                      </form:form>
	                     </tr>
	          </table>  
   </div>
</body>
<!-- End MOHAMAD Code  -->
</html>