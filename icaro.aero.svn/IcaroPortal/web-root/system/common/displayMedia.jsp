<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
    <title><bean:message key="label.uploadFile" bundle="messages" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/system.css">
    
    <script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/tools.js"></script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/validator.jsp"></script>
	
<script language="javascript">
	function updateParentValues(){
		var media = document.getElementById("media");
		parent.updateValues(media.width, media.height)
	}
</script>

</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" <%if (request.getParameter("update")!=null && request.getParameter("update").equals("true")) {%>onload="updateParentValues();"<%}%> >
	<% String size = "";
	   if (request.getParameter("width")!=null && request.getParameter("width").length()>0){
			size = " width=\""+request.getParameter("width")+"\"";
	   } 
	   if (request.getParameter("height")!= null && request.getParameter("height").length()>0){
	   		size = size + " height=\""+request.getParameter("height")+"\"";
	   }
	%>
	<% if (request.getParameter("isFlash")!=null && request.getParameter("isFlash").equals("true")) {%>
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" <%=size%> id="media">
              <param name="movie" value="<%=request.getContextPath()%><%=request.getParameter("path")%>">
              <param name="quality" value="high">
			  <param name="wmode" value="transparent">
              <embed src="<%=request.getContextPath()%><%=request.getParameter("path")%>" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" <%=size%> wmode="transparent"></embed>
			</object>
	<% } else {%>
			<img src="<%=request.getContextPath()%><%=request.getParameter("path")%>" border="0" id="media" <%=size%>>
	<% } %>
</body>
</html>