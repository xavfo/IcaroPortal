<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<span id="applications" class="normal" >
<select name="${targeProperty}" size="1" style="width: 250px;" class="input">
	<c:forEach var="item" items="${listOfOptions}" varStatus="status">
		<option value="${item.code}">${item.name}</option>
	</c:forEach>
</select>
</span>
<% request.removeAttribute("listOfOptions"); %>



