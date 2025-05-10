<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
	<title>Snoop</title>
</head>

<body>
Real Path: <%= request.getRealPath("/")  %><br>
Context Path: "${pageContext.request.contextPath}" 2+3=${2+3}<br>
TEST <%= new java.util.Date() %><br><br>
<strong>Application</strong><br>
<%
java.util.Enumeration names = application.getAttributeNames();
while( names.hasMoreElements() ) {
%>
    <%= names.nextElement() %><br>
<%
}
%>
<br><br><br>
<strong>Session</strong><strong></strong><br>
<%
names = session.getAttributeNames();
String attribute = "";
while( names.hasMoreElements() ) {
attribute= (String)names.nextElement();
%>
    <strong><%= attribute%></strong>:&nbsp;<%= session.getAttribute(attribute) %><br>
<%
}
%>

</body>
</html>
