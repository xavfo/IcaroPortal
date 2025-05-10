<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.menu" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.menu" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>


<html:form action="/system/portal/menu">
<html:hidden property="action" value="" />
<html:hidden property="tab" value="1" />
<!-- Options -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		</td>
		<td>
			<tiles:insert definition="system.navBar">
				<tiles:put name="edit" 		value="true" />
			</tiles:insert>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
		<th width="30">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th><bean:message key="label.name" bundle="messages" /></th>
		<th><bean:message key="label.enabled" bundle="messages" /></th>
		<%-- th><bean:message key="label.item" bundle="messages" /></th --%>
		<th width="120"><bean:message key="label.accesses" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${menuList}" varStatus="status">
		<c:url value="/system/portal/menu.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
			<c:param name="tab" value="1" />
		</c:url>
		<%-- c:url value="/system/portal/menu/item.do" var="urlItem">
			<c:param name="action" value="list" />
			<c:param name="code" value="${item.code}" />
			<c:param name="tab" value="2" />
		</c:url --%>
		<c:url value="/system/portal/menu/access.do" var="urlBanner">
			<c:param name="action" value="list" />
			<c:param name="code" value="${item.code}" />
			<c:param name="tab" value="2" />
		</c:url>
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center">
					<html:checkbox property="codes" value="${item.code}" />
			</td>
			<td><a href="${urlRead}" class="item">${item.name}</a></td>
			<td width="80" align="center">
				<c:choose>
					<c:when test="${item.enabled == true}">
						<bean:message key="label.yes" bundle="messages" />
					</c:when>
					<c:otherwise>
						<bean:message key="label.no" bundle="messages" />
					</c:otherwise>
				</c:choose>
			</td>
			<%-- td width="150" align="center"><a href="${urlItem}" class="item"><bean:message key="label.item" bundle="messages" /></a></td --%>
			<td align="center"><a href="${urlBanner}" class="item"><bean:message key="label.accesses" bundle="messages" /></a></td>
		</tr>
	</c:forEach>
</table>
</html:form>