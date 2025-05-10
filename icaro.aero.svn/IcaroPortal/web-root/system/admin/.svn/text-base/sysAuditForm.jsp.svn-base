<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<script language="javascript">
function cancel(frm) {
	var button =  document.getElementById('inputCancel');
	button.click();
}
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="audit.adminAudit" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="audit" bundle="systemMenu" /> >
			<bean:message key="audit.adminAudit" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>


<html:form action="/system/admin/sysAudit">
<html:hidden property="action" value="list" />
<table border="0" cellpadding="4" cellspacing="0" width="100%">
<tr>
  <td align="right">
  	<span style="display:none"><html:cancel styleId="inputCancel"><bean:message key="button.cancel" bundle="messages" /></html:cancel></span>
  	<table border="0" cellpadding="2" cellspacing="0">
	<tr>
		<td align="center"><a href="javascript: cancel(document.forms[0]);"><img src="${pageContext.request.contextPath}/images/system/icons/cancelar.gif" alt="<bean:message key="button.cancel" bundle="messages" />" width="26" height="27" border="0" /></a></td>
	</tr>
	<tr>
		<td align="center"><a href="javascript: cancel(document.forms[0]);"><bean:message key="button.cancel" bundle="messages" /></a></td>
	</tr>
</table>
  </td>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		<html:messages id="message" message="true">
			<div class="message"><c:out value="${message}" escapeXml="false" /></div>
		</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			Informaci&oacute;n Auditor&iacute;a
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
	<tr>
		<td>
		<table align="right" border="0" cellpadding="4" cellspacing="0" width="100%">
			<tr>
				<td width="25%">
					<strong>Usuario:</strong><br>
					<div class="box" style="width: 100px;">${sysAudit.log.userName}&nbsp;</div>
				</td>	
			</tr>
			<tr>
				<td width="25%">
					<strong>Role:</strong><br>
					<div class="box" style="width: 100px;">${sysAudit.log.roleName}&nbsp;</div>
				</td>	
				<td width="25%">
					<strong>Fecha:</strong><br>
					<div class="box" style="width: 120px;"><fmt:formatDate value="${sysAudit.date.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/></div>
				</td>	
				<td width="25%">
					<strong>Host:</strong><br>
					<div class="box" style="width: 100px;">${sysAudit.remoteHost}&nbsp;</div>
				</td>		
				<td width="25%">
					<strong>Direcci&oacute;n Remota:</strong><br>
					<div class="box" style="width: 100px;">${sysAudit.remoteAddress}&nbsp;</div>
				</td>		
			</tr>
			<tr>
				<%--
				<td width="25%">
					<strong><bean:message key="label.module" bundle="messages" /></strong><br>
					<div class="box">${sysAudit.module}&nbsp;</div>
				</td>
				--%>
				<td width="25%">
					<strong><bean:message key="label.service.function" bundle="messages" /></strong><br>
					<div class="box"><bean:message key="${sysAudit.className}"/>&nbsp;</div>
				</td>
				<td width="25%">
					<strong><bean:message key="label.name" bundle="messages" />:</strong><br>
					<div class="box">${sysAudit.entityName}&nbsp;</div>
				</td>	
				<td width="25%">
					<strong>Acci&oacute;n:</strong><br>
					<div class="box" style="width: 100px;">${sysAudit.action}&nbsp;</div>
				</td>	
				<td width="25%">
					<strong>M&eacute;todo:</strong><br>
					<div class="box" style="width: 100px;">${sysAudit.method}&nbsp;</div>
				</td>	
			</tr>	
			<tr>
				<td colspan="2">
				<strong>Url:</strong><br>
				<div class="box">${sysAudit.url}&nbsp;</div>
				</td>	
				<td colspan="2">
				<strong>Uri:</strong><br>
				<div class="box">${sysAudit.uri}&nbsp;</div>
				</td>	
			</tr>
		</table>
		</td>
	</tr>	
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
</table>
</html:form>