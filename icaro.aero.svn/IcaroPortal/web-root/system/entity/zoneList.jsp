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
<html:form action="/system/information/zone">
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="85%">
<tr>
<td width="85%">&nbsp;</td>
    <td align="center"><a href="javascript: doAction(zoneForm, 'action', 'create')"><img src="${pageContext.request.contextPath}/images/system/icons/anadir.gif" alt="Add" width="26" height="27" border="0" /></a></td>
    <td align="center"><a href="javascript: doActionN(zoneForm, 'action', 'read', 'codes')"><img src="${pageContext.request.contextPath}/images/system/icons/editar.gif" alt="Edit" width="26" height="27"  border="0"/></a></td>
    <td align="center"><a href="javascript: doActionN(zoneForm, 'action', 'delete', 'codes')"><img src="${pageContext.request.contextPath}/images/system/icons/eliminar.gif" alt="Delete" width="26" height="27" border="0" /></a></td>
</tr>
<tr>
	<td width="85%">&nbsp;</td>
    <td align="center"><a href="javascript: doActionN(zoneForm, 'action', 'create')"><bean:message key="button.add" bundle="messages" /></a></td>
    <td align="center"><a href="javascript: doActionN(zoneForm, 'action', 'read', 'codes')"><bean:message key="button.edit" bundle="messages" /></a></td>
    <td align="center"><a href="javascript: doActionN(zoneForm, 'action', 'delete', 'codes')"><bean:message key="button.delete" bundle="messages" /></a></td>
</tr>
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
<html:hidden property="action" value="" />
<html:hidden property="cityCode" value="${zoneForm.cityCode}" />
<!-- Options -->
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
	<tr>
		<th width="94">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
	  <th width="382"><bean:message key="label.name" bundle="messages" /></th>
	  <th width="80"><bean:message key="label.enabled" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${zoneList}" varStatus="status">		
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td>${item.name}</td>
			<td>
				<c:choose>
						<c:when test="${item.isEnabled == true}">
							<bean:message key="label.yes" bundle="messages" />
						</c:when>
						<c:otherwise>
							<bean:message key="label.no" bundle="messages" />
						</c:otherwise>
					</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>
</html:form>