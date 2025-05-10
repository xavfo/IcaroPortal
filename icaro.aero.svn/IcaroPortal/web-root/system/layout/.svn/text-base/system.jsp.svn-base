<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!------------------------------------------------------------------------>
<!-- Copyright © Yage. All Rights Reserved.                             -->
<!--                                                                    -->
<!-- YAGE evolucion digital                                             -->
<!-- Av. Brasil 951 y Mariano Echeverría                                -->
<!-- Quito-Ecuador                                                      -->
<!-- www.yage.com.ec                                                    -->
<!------------------------------------------------------------------------>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
    <title><bean:message key="window.title" bundle="systemHelp" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/system.css">
	<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/dtree.css">
	<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/calendar.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/newStyle.css">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/favicon.ico">

	<script language="JavaScript1.2" vqm_id="open">
		CONTEXT_PATH = '${pageContext.request.contextPath}';
		vqm__codebase = '${pageContext.request.contextPath}/js/';
		vqm__imagebase = '${pageContext.request.contextPath}/images/system/dqmmenu/';
		vqm__urlbase = CONTEXT_PATH;
		dtree_imagebase = '${pageContext.request.contextPath}/images/system/dtree/';
	</script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/tools.js"></script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/validator.jsp"></script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/dtree.js"></script>
	<script language="JavaScript1.2" vqm_id="" src="${pageContext.request.contextPath}/js/system_menu.jsp"></script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/tdqm_loader.js"></script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/calendar/calendar.js"></script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/calendar/lang/calendar-${appLanguage.locale}.js"></script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/calendar/calendar-setup.js"></script>
	<script language="JavaScript1.2" vqm_id="open">
		dtree_imagebase = '${pageContext.request.contextPath}/images/system/dtree/';
	</script>
	<script language="JavaScript1.2">
		function initPage() {
			doResize();
		}
		
		function doResize() {
			var ifr = document.getElementById("itree");
			if (ifr != undefined) {
				var ifr2 = document.getElementById("iform");
				//var h = document.body.offsetHeight - 280;
                var h = window.innerHeight - 290;
				if (h < 150) 
					h = 150;
				ifr.height = h;
				ifr2.height = h;
			}
			//window.alert('entro');
		}
		//window.onresize=doResize();
	</script>
</head>

<body onResize="doResize()">
	<table align="center" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
		<tr>
			<td bgcolor="#999999" width="1"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="450" border="0"></td>
			<td>
				<table bgcolor="#FFFFFF" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
					<tr>
						<td height="1"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="760" height="1" border="0"></td>
					</tr>
					<tr>
						<td><tiles:insert attribute="header" /></td>
					</tr>
					<tr>
						<td height="100%" valign="top" style="padding: 10px 10px 25px 10px;">
							<tiles:insert attribute="body" />
						</td>
					</tr>
					<tr>
						<td height="25"><tiles:insert attribute="footer" /></td>
					</tr>
				</table>
			</td>
			<td bgcolor="#999999" width="1"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="1" border="0"></td>
		</tr>
	</table>
</body>
</html>
