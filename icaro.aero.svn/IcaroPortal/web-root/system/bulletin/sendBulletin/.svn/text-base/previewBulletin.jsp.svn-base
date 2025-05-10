<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
    <title>.::<bean:message key="window.title" bundle="systemHelp" />::.</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    
	<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/system.css">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/favicon.ico">
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/tools.js"></script>
</head>

<body bgcolor="#ffffff">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<!-- fwtable fwsrc="bolet’n.png" fwbase="bolet’n.jpg" fwstyle="Dreamweaver" fwdocid = "1155256067" fwnested="0" -->
  <tr>
   <td><img src="${pageContext.request.contextPath}/images/bulletin/layout/spacer.gif" width="607" height="1" border="0" alt=""></td>
   <td><img src="${pageContext.request.contextPath}/images/bulletin/layout/spacer.gif" width="1" height="1" border="0" alt=""></td>
  </tr>

  <tr>
   <td>
     <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#1A2A8C">
       <tr>
         <td width="176"><img name="header" src="${pageContext.request.contextPath}/images/bulletin/layout/header.gif" width="165" height="51" border="0" alt=""></td>
         <td width="100%" bgcolor="#1A2A8C">&nbsp;</td>
         <td width="197"><div align="right"><img src="${pageContext.request.contextPath}/images/bulletin/layout/boletin.gif" width="15" height="51"></div></td>
       </tr>
     </table></td>
   <td><img src="${pageContext.request.contextPath}/images/bulletin/layout/spacer.gif" width="1" height="51" border="0" alt=""></td>
  </tr>
  <tr>
   <td background="${pageContext.request.contextPath}/images/bulletin/layout/sep.gif"><img src="${pageContext.request.contextPath}/images/bulletin/layout/sep.gif" width="2" height="13"></td>
   <td><img src="${pageContext.request.contextPath}/images/bulletin/layout/spacer.gif" width="1" height="13" border="0" alt=""></td>
  </tr>
  <tr>
   <td><img name="sep2" src="${pageContext.request.contextPath}/images/bulletin/layout/sep2.gif" width="607" height="4" border="0" alt=""></td>
   <td><img src="${pageContext.request.contextPath}/images/bulletin/layout/spacer.gif" width="1" height="4" border="0" alt=""></td>
  </tr>
  <tr>
   <td><table width="100%" height="91" border="0" cellpadding="0" cellspacing="0">
   	<c:choose>
		<c:when test="${!empty bulletin.image}">
			<tr>
		       <td width="18%" rowspan="2"><c:if test="${!empty bulletin.image}"><img src="${pageContext.request.contextPath}${bulletin.image}"></c:if></td>
		       <td colspan="2"><span class="title2">${bulletin.topic.name}</span></td>
		     </tr>
		</c:when>
		<c:otherwise>
			<tr>
		       <td colspan="2"><span class="title2">${bulletin.topic.name}</span></td>
		     </tr>
		</c:otherwise>
	</c:choose>     
     <tr>
       <td width="4%">&nbsp;</td>
       <td width="78%"><span class="subtitle">${bulletin.title}<br>
       </span>${bulletin.content}</td>
     </tr>
   </table></td>
   <td><img src="${pageContext.request.contextPath}/images/bulletin/layout/spacer.gif" width="1" height="167" border="0" alt=""></td>
  </tr>
  <tr>
   <td><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
     <tr>
       <td width="176"><img name="footer" src="${pageContext.request.contextPath}/images/bulletin/layout/footer.gif" width="203" height="22" border="0" alt=""></td>
       <td width="100%" bgcolor="#FFFFFF">&nbsp;</td>
       <td width="197"><div align="right"><img src="${pageContext.request.contextPath}/images/bulletin/layout/sri.gif" width="126" height="22"></div></td>
     </tr>
   </table></td>
   <td><img src="${pageContext.request.contextPath}/images/bulletin/layout/spacer.gif" width="1" height="22" border="0" alt=""></td>
  </tr>
</table>
</body>
</html>

