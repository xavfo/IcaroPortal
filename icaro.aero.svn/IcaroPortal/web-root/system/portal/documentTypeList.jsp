<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="information.documentType" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			<bean:message key="information.documentType" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.information.documentType" bundle="systemHelp" />
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>


<html:form action="/system/portal/documentType">
<html:hidden property="action" value="" />
<html:hidden property="code" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		</td>
		<td align="right">
			<tiles:insert definition="system.navBar">
				<tiles:put name="add" 		value="true" />
				<tiles:put name="edit" 		value="true" />
				<tiles:put name="delete" 	value="true" />
			</tiles:insert>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>


<!-- List data -->
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
		<th width="30">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th><bean:message key="label.name" bundle="messages" /></th>
		<th width="50"><bean:message key="label.icon" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${documentTypeList}" varStatus="status">
		<c:url value="/system/portal/documentType.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url>
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td><a href="${urlRead}" class="item">${item.name}</a></td>
			<td align="center"><img src="${pageContext.request.contextPath}${item.icon}" border="0" width="20" height="20"></td>
		</tr>
	</c:forEach>
</table>
</html:form>