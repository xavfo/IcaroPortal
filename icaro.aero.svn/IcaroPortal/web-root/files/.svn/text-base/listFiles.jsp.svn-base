<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- %@ page import="com.yage.file.FileExtensionUtils" --%>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Properties" %>

<% java.util.Date today = new java.util.Date ();  
	request.setAttribute("today",today); %>

<html>
<head>
	<title>Untitled</title>
</head>

<body>
<%--TEST <%= new java.util.Date() %><br><br>
equals:    <%= date.equals(date2) %><br>
equals Null <%= date.equals(null) %><br>
--%>


<c:set value="date2" var="date3" />
<c:out value="${today}" /><br><br>

<%--	<strong>date & date2</strong>: <c:out value="${today == date2}" /><br>

<strong>date2 & date3:</strong> <c:out value="${date3 == date2 }" /><br>
<strong>date3:</strong><em><c:out value="${empty date3}" /></em><br>

<a href="listFiles.jsp?listFiles=true">listar directorio de extensiones:</a>


<% if (request.getParameter("listFiles") != null) { 
	//FileExtensionUtils.loadFileExtentionsAvailable("/images/fileTypes", null,  application);
%>
--%><table cellspacing="0" cellpadding="2" border="1">
<tr class="header" align="left">
    <th  align="right">#&nbsp;</th>
    <th>Extension&nbsp;</th>
    <th>Archivo Icono &nbsp;</th>
	<th>&nbsp;</th>
</tr>
<% 
		//Properties ext = FileExtensionUtils.getFileExtentions();
		Properties ext = (Properties)application.getAttribute("iconFileExtensions");
		Enumeration enumeration = ext.keys();
		String key = "";
		int count = 0;
		while (enumeration.hasMoreElements() ) {
			key = (String) enumeration.nextElement();%>
			<tr>
				<td><%= ++count %>.-&nbsp;&nbsp;</td>
				<td><strong><%= key %></strong></td>
				<td><%=  ext.getProperty(key) %></td>
				<td><img src="<%= request.getContextPath() %><%=  ext.getProperty(key) %>" border="0" alt="<%= key %>"></td>
			</tr>
	
			<%--<%= ++count %>.-&nbsp;<%= key %>:&nbsp;<%= props.getProperty(key) %><br>	--%>			
	<%	} %>
</table>
	
<%--<% } %>--%>
</body>
</html>
