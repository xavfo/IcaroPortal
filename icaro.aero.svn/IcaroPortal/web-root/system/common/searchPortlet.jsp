<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=iso-8859-1">
    <title>.::<bean:message key="window.title" bundle="systemHelp" />::.<bean:message key="label.search.portlet" bundle="messages" /></title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    
	<link rel="STYLESHEET" type="text/css" href="${pageContext.request.contextPath}/css/system.css">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/favicon.ico">
	<script language="JavaScript1.2">

		CONTEXT_PATH = '${pageContext.request.contextPath}';

		function setResponse(url) {
			var oE = window.opener.oEditor;
			var i= document.forms[0].alignOption.selectedIndex;
			oE.InsertHtml("<iframe src="+ encodeURI(url)+" align="+ document.forms[0].alignOption.options[i].value +" width='"+ document.forms[0].width.value + "' height='"+ document.forms[0].height.value +"' > </iframe>");	
			self.close();
		}
		function searchPortlet(frm) {
			frm.submit();
		}		
		
	</script>
	<script language="JavaScript1.2" src="${pageContext.request.contextPath}/js/tools.js"></script>
</head>

<body>
<table border="0" cellpadding="0" cellspacing="0" width="100%" align="left">
<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" height="10"></td></tr>
<tr>
	<td class="subtitle">&nbsp;&nbsp;<bean:message key="label.search.portlet" bundle="messages" /><img src="${pageContext.request.contextPath}/images/shim.gif" height="10"></td>
</tr>
<tr><td><img src="${pageContext.request.contextPath}/images/shim.gif" height="10"></td></tr>
<tr>
	<td>
		<table cellpadding="0" cellspacing="0" border="0" align="center" width="97%">		
		<tr>
			<td>
			<html:form action="/popup/searchPortlet" styleId="searchForm" onsubmit="searchPortlet(this);">
			<html:hidden property="action" value="search" />
			<html:hidden property="parent" value="${param.parent}" />
			<html:hidden property="form"   value="${param.form}" />
			
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_iz.gif" width="7" height="7" alt="" border="0"></td>
					<td background="${pageContext.request.contextPath}/images/search/tile_superior.gif" width="100%"><img src="${pageContext.request.contextPath}/images/shim.gif" width="100%" height="7" alt="" border="0"></td>
					<td><img src="${pageContext.request.contextPath}/images/search/borde_superior_der.gif" width="7" height="7" alt="" border="0"></td>
				</tr>
				<tr>
					<td background="${pageContext.request.contextPath}/images/search/tile_iz.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7" height="100%" alt="" border="0"></td>
					<td width="100%" bgcolor="#EBEEF3">
						<table border="0" cellpadding="0" cellspacing="10">
							<tr>
								<td>
									<bean:message key="label.name" bundle="messages" /><br>
									<html:text property="name" styleClass="input" style="width: 120px;" maxlength="100" />
									<input type="image" src="${pageContext.request.contextPath}/images/search/boton_go.gif" width="37" height="19" border="0" name="sbmtFind" id="sbmtFind">
								</td>
								<td>
									<bean:message key="label.width" bundle="messages" /><br>
									<html:text property="width" styleClass="input" style="width: 30px;" maxlength="30" />
								</td>
								<td>
									<bean:message key="label.height" bundle="messages" /><br>
									<html:text property="height" styleClass="input" style="width: 30px;" maxlength="30" />
								</td>
								
								<td>
									<bean:message key="option.all" bundle="messages" /><br>
									<html:select property="alignOption" size="1" styleClass="input" style="width: 120px;" >
										<html:option value="right"><bean:message key="option.right" bundle="messages" /></html:option>
										<html:option value="left"><bean:message key="option.left" bundle="messages" /></html:option>
										<html:option value="center"><bean:message key="option.center" bundle="messages" /></html:option>
									</html:select>
								</td>
								
							</tr>
						</table>					
					</td>
					<td background="${pageContext.request.contextPath}/images/search/tile_der.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="7" height="100%" alt="" border="0"></td>
				</tr>
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/search/borde_inferior_iz.gif" width="7" height="7" alt="" border="0"></td>
					<td background="${pageContext.request.contextPath}/images/search/tile_inferior.gif" width="100%"><img src="${pageContext.request.contextPath}/images/shim.gif" width="100%" height="7" alt="" border="0"></td>
					<td><img src="${pageContext.request.contextPath}/images/search/borde_inferior_der.gif" width="7" height="7" alt="" border="0"></td>
				</tr>
			</table>
			</html:form>
			</td>
		</tr>
		<tr><td colspan="4"><img src="${pageContext.request.contextPath}/images/shim.gif" height="10"></td></tr>
		<tr>
			<td height="145" align="center" valign="top">
				<c:choose>
					<c:when test="${empty portletList && portletSearchForm.action eq 'search'}">
						<h3 class="message"><bean:message key="message.empty.records" /></h3>
					</c:when>
					<c:otherwise>
						<!--inicio listado-->
						<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
							<tr>
								<th width="50">#</th>																												
								<th><bean:message key="label.portlet.name" bundle="messages" /></th>	
								<th width="120" align="center"><bean:message key="link.content.link" bundle="messages" /></th>
							</tr>
							<!--Fin Header-->
							<script language="JavaScript1.2">
									var codes = new Array(1);
									var labels = new Array(2);
							</script>
							<c:forEach var="item" items="${portletList}" varStatus="status">
							<script language="JavaScript1.2">
 									codes[0]  = ""; 
									labels[0] = ""; 
									labels[1] = "";
							</script>	
							<!-- detalle registros -->
							<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
								<td align="center" style="padding-left:5px">
									<%--
									<a href="javascript:setResponse(new Array('${item.code}'),new Array('${item.url}', document.forms[0].alignOption.value))">
									--%>
									<a href="javascript:setResponse('${item.url}')">
										<img src="${pageContext.request.contextPath}/images/system/icons/editar.gif" border="0" height="15" width="15"></a></td>
								<td style="padding-left:5px">
									${item.title}
								</td>	
								<td style="padding-left:5px">
									<c:set var="delim" value="http"/>
									<c:if test="${! fn:contains(item.url, delim)}">
										<a href="${pageContext.request.contextPath}${item.url}" target="_blank" alt="${item.url}">${item.url}</a>
									</c:if>
									<c:if test="${fn:contains(item.url, delim)}">
										<a href="${item.url}" target="_blank" alt="${item.url}">
											${item.url}
										</a>
									</c:if>
								</td>
							</tr>
							<!-- FIN detalle registros -->
							</c:forEach>
						</table>						
						<!--fin listado-->
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</table>
   </td>
</tr>	
</table>
</body>
</html>

<script language="JavaScript1.2">	
//	initPage();
</script>

