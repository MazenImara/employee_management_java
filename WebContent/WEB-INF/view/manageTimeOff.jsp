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
<title>Add Time Off</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#addTimeOff').click(function(){
		$('.error').remove();  // remove old write error
		var dateFrom = $('#dateFrom').val();
		var dateTo = $('#dateTo').val();
		var addTimeOffForm = $('#addTimeOffForm');
		var currentDate = new Date();
		var dateFromObj = new Date(dateFrom)
		var dateToObj = new Date(dateTo)
		if(dateFrom != '' && dateTo != ''){
			if(currentDate.getTime() <= dateFromObj.getTime() && currentDate.getTime() <= dateToObj.getTime()){
		        if(dateFrom < dateTo){
		        	addTimeOffForm.submit();
		        }
		        else{
		        	$('#errorDiv').append('<h6 class="error">* plase inter from date lower than To date</h6>')
		        }				
			}
			else{
				$('#errorDiv').append('<h6 class="error">* please enter future dates</h6>')
			}

		}
		else{
			$('#errorDiv').append('<h6 class="error">* wrong date input</h6>')
			
		}
    });
});
</script>
</head>
<body>
<!--MOHAMAD Code  -->


<jsp:include  flush="true" page="/WEB-INF/view/header.jsp"/>

   <div align="center">
	     <h1 ><i>Your TimeOff <b></b></i></h1>
		     <table border="1">
			      <tr class="staticInfoTable">
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
						   <td><input type="submit" value="DELETE   "/></td>
	                     </tr> 
	                      </form:form>
	                      </c:forEach>
	                     <tr>
		                      <form:form id='addTimeOffForm' name="formaddtimeoff" method="post" action="addtimeoff" modelAttribute="timeOff">
		                          <td>${status.index + 2}</td>
			                      <input type='hidden' name="employeeId" value='${log.employee.id}'/>
			                      <td><input type='datetime-local' id="dateFrom"   name = "date1" value='${date1}'required/></td>
			                      <td><input type='datetime-local' id="dateTo"  name = "date2" value='${date2}'required/></td>
			                      <td ><span id="addTimeOff"> Add </span></td> 
		                      </form:form>
	                     </tr>
	          </table> 
	          <div id='errorDiv' style='color:red'></div> 
   </div>
</body>
<!-- End MOHAMAD Code  -->
</html>