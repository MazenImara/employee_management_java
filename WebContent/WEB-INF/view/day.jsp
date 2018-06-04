<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<div align="center">
	     <h1 Style=' color :blue' ><i>Days<b></b></i></h1>
		     <table border="1">
			      <tr>
			          <th>S.No.</th>
		                <th>employee_id</th>
		                <th>check </th>
		                <th>date </th>
		                <th> start</th>
		                <th>endtime </th>
		                <th>timespend </th>
		                <th>period1  </th>
		                <th>period2 </th>
		                </tr>
		                <c:forEach var="day" items="${days}" varStatus="status">
		           <form:form name="addday" method="post" action="addDay" modelAttribute="day">
					      <tr>
				              <td>${status.index + 1}</td>
				              <input type='hidden' name="id" value='${day.id}'/>
				              <td><input type='text' name="employee_id" value='${day.employee_id}'/></td>
				              <td><input type='text' name="check" value='${day.check}'/></td>
				             <td><input type='text' name="timeSpend" value='${day.date}'/>${day.timeSpend}</td>                   
				            <td><input type='text' name="status" value='${day.start}'> </td>
				              <td><input type='text' name="timeSpend" value='${day.endtime}'/>${day.timeSpend}</td> 
				              <td><input type='text' name="timeSpend" value='${day.period1}'/>${day.period2}</td>                   
				                                
				              <td style="color:blue"><input type="submit" value="Add"/></td>
					       </tr> 
		                  </form:form>
                                </c:forEach>
</body>
</html>