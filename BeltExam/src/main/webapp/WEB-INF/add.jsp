<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
    <h1>Create a New TV Show</h1>
	<form:form action="/create/show" method="post" modelAttribute="tvShow">
	<form:input path="user" type="hidden" value="${user_id}"/>
		    <p>
		        <form:label path="title">Title</form:label>
		        <form:errors path="title"/>
		        <form:input path="title"/>
		    </p>
		     <p>
		        <form:label path="network">Network</form:label>
		        <form:errors path="network"/>
		        <form:input path="network"/>
		    </p> 
		    <p>
		        <form:label path="description">Description</form:label>
		        <form:errors path="description"/>
		        <form:textarea path="description"/>
		    </p>
		    <a href="/home">Cancel</a>
		    <br>
		    <input type="submit" value="Submit"/>
		</form:form> 
	
    </div> <!-- End of Container -->
</body>
</html>