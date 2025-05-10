<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="information.zone" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			<bean:message key="information.zone" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="4" cellspacing="0" width="100%">
<tr>
  <td width="85%">&nbsp;</td>
  <td align="center"><a href="javascript: doActionN(document.zoneForm, 'action', 'save')"><img src="${pageContext.request.contextPath}/images/system/icons/guardar.gif" alt="Save" width="26" height="27" border="0" /></a></td>
  <td align="center"><a href="javascript: doActionN(document.zoneForm, 'action', 'apply')"><img src="${pageContext.request.contextPath}/images/system/icons/aplicar.gif" alt="Apply" width="26" height="27" border="0" /></a></td>
  <td align="center"><a onclick="document.zoneForm.reset();return false;" href="#"><img src="${pageContext.request.contextPath}/images/system/icons/restaurar.gif" alt="Reset" width="26" height="27" border="0" /></a></td>
  <td align="center"><a href="javascript: doActionN(document.zoneForm, 'action', 'list')"><img src="${pageContext.request.contextPath}/images/system/icons/cancelar.gif" alt="Cancel" width="26" height="27" border="0" /></a></td>
<tr>
	<td width="85%">&nbsp;</td>
    <td align="center"><a href="javascript: doActionN(document.zoneForm, 'action', 'save')"><bean:message key="button.save" bundle="messages" /></a></td>
	<td align="center"><a href="javascript: doActionN(document.zoneForm, 'action', 'apply')">Apply</a></td>
    <td align="center"><a onclick="document.zoneForm.reset();return false;" href="#"><bean:message key="button.reset" bundle="messages" /></a></td>
    <td align="center"><a href="javascript: doActionN(document.zoneForm, 'action', 'list')">Cancelar</a></td>
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
			<c:choose>
				<c:when test="${!empty zoneForm.code && zoneForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${zoneForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/information/zone/save" focus="name" onsubmit="return validateCountryForm(this);">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="cityCode" value="${cityCode}" />
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td height="30" width="170"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
	</tr>	
	<tr>
		<td class="optional"><bean:message key="prompt.enabled" /> :</td>
		<td>
			<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><hr width="100%" size="1"></td>
	</tr>
	<tr>
		<td></td>
		<td height="50">
			<html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.ok" bundle="messages" /></html:submit>
			<html:cancel styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.cancel" bundle="messages" /></html:cancel>
			<html:reset styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.reset" bundle="messages" /></html:reset>
		</td>
	</tr>
</table>
</html:form>

