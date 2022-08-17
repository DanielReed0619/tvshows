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
<title>Dashboard</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
    	<h1>Welcome, <c:out value="${user.userName}">${user.userName}</c:out></h1><br>
    	<a href="/logout">log out</a>
    	
    	<br><br>
    	<h6>TV Shows</h6>
    	<table>
    		<tr>
    			<th>Show</th>
    			<th>Network</th>
    		</tr>
	    	<c:forEach var="tvShow" items="${tvShows}">
	    		<tr>
	    			<td><a href="/show/${tvShow.id}"><c:out value="${tvShow.title}">${tvShow.title}</c:out></a></td>
	    			<td><c:out value="${tvShow.network}">${tvShow.network}</c:out></td>	
	    		</tr>
	    	</c:forEach>
    	</table>
    	<br><br>
        <a href="/shows/new">Add a Show</a>
    </div> <!-- End of Container -->
</body>
</html>