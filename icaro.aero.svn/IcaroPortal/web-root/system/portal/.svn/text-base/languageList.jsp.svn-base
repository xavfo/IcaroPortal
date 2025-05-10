<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.language" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.language" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.portal.language" bundle="systemHelp" />
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>


<html:form action="/system/portal/language">
<html:hidden property="action" value="" />
<!-- Options -->
<div>
	<html:button property="button" styleClass="button" onclick="doAction(this.form, 'action', 'create')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.add" bundle="messages" /></html:button>
	<html:button property="button" styleClass="button" onclick="doAction(this.form, 'action', 'delete')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.delete" bundle="messages" /></html:button>
	<html:button property="button" styleClass="button" onclick="doAction(this.form, 'action', 'setDefault')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.default" bundle="messages" /></html:button>
</div>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
		<th width="30">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th><bean:message key="label.name" bundle="messages" /></th>
		<th width="50"><bean:message key="label.iso" bundle="messages" /></th>
		<th width="120"><bean:message key="label.default" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${languageList}" varStatus="status">
		<c:url value="/system/portal/language.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url>
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center">
				<c:if test="${!item.default}">
					<html:checkbox property="codes" value="${item.code}" />
				</c:if>
			</td>
			<td><a href="${urlRead}" class="item">${item.name}</a></td>
			<td>${item.locale}</td>
			<td align="center">
				<c:choose>
					<c:when test="${item.default}">
						<input type="radio" name="defaultCode" value="${item.code}" checked>
					</c:when>
					<c:otherwise>
						<input type="radio" name="defaultCode" value="${item.code}">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</table>
</html:form>