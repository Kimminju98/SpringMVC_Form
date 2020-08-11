<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script>
function delete_ok(id){
	if(confirm("정말로 삭제하시겠어요? ")){
		location.href='deleteemp/'  + id;
	}
}
</script>
</head>
<body>

<h1>Employees List</h1>
<table border="2" width="70%" cellpadding="2">
<tr><th>Id</th><th>Name</th><th>Salary</th><th>Designation</th><th>RegDate</th><th>Edit</th><th>Delete</th></tr>

	<c:forEach var="emp" items="${list}">
	<tr>
	<td>${emp.id}</td>
	<td>${emp.name}</td>
	<td>${emp.salary}</td>
	<td>${emp.designation}</td>
	<td>${emp.regdate}</td>
	<td><a href="editemp/${emp.id}">Edit</a></td>
	<td><a href="#" onclick="javascript:delete_ok('${emp.id}')">Delete</a></td>
	</tr>
	</c:forEach>
</table>
<br/>
<a href ="empform">Add New Employee</a>

</body>
</html>