<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.section" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> &gt;
			<bean:message key="portal.section" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" style="color:#999999" noshade="noshade" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.portal.section" bundle="systemHelp" /><br/>
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"/><br/>
<br/>
<html:form action="/system/portal/section">
<html:hidden property="action" value="" />
<html:hidden property="level" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		</td>
		<td>
			<tiles:insert definition="system.navBar">
				<tiles:put name="add" 		value="true" />
				<tiles:put name="edit" 		value="true" />
				<tiles:put name="delete" 	value="true" />
			</tiles:insert>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"/><br/>

<%-- List data --%>
<table align="center" class="list" border="0" cellpadding="2" cellspacing="1" width="95%">
	<tr>
		<th width="30">#</th>
		<th width="20"><input type="checkbox" name="checkAll" onclick="setCheckBoxValue(this.form, 'codes', this.checked)"/></th>
		<th width="150"><bean:message key="label.section" bundle="messages" /></th>
		<th width="150"><bean:message key="label.order" bundle="messages" /></th>
		<th width="110"><bean:message key="prompt.enabled" /></th>
	</tr>
	<c:forEach var="item" items="${contentList}" varStatus="status">		
		<c:url value="/system/portal/section.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
			<c:param name="tab" value="1" />
		</c:url>
		<tr class="item" onmouseover="swapClass(this, 'item', 'itemhi')" onmouseout="swapClass(this, 'item', 'itemhi')">
			<td align="center">${status.count}</td>
			<td align="center" width="20"><html:checkbox property="codes" value="${item.code}" /></td>
			<td><a href="${urlRead}" class="item">${item.title}</a></td>
			<td align="center">${item.order}</td>
			<td align="center">
				<c:choose>
					<c:when test="${item.enabled == true}">
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

<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"/><br/>

<table align="center">
	<tr>
		<td>
			<tiles:insert definition="system.pager">
				<tiles:put name="styleClass" value="pageItem" />
				<tiles:put name="selectedClass" value="pageSelItem" />
				<tiles:put name="totalPages" value="${contentForm.totalPages}" />
				<tiles:put name="currentPage" value="${contentForm.pageNumber}" />
				<tiles:put name="formIndex" value="0" />
			</tiles:insert>
		</td>
	</tr>
</table>
</html:form>