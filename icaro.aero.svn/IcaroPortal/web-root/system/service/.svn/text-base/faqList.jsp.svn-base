<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="service.faq" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="service.faq" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.service.faq" bundle="systemHelp" />
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<html:form action="/system/service/faq">
<html:hidden property="action" value="list" />
<!-- Find -->
<table bgcolor="#CCCCCC" border="0" cellpadding="4" cellspacing="1" width="100%">
	<tr bgcolor="F3F3F3">
		<td>
			<img src="<%= request.getContextPath() %>/images/icons/find.gif" width="16" height="16" border="0" align="absmiddle"> <span class="subtitle"><bean:message key="label.search" bundle="messages" />:</span><br>
			&nbsp;
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<bean:message key="prompt.question" /><br>
						<html:text property="question" styleClass="input" style="width: 200px;" />
					</td>
					<td width="10"></td>
					<td><br><html:submit styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.find" bundle="messages" /></html:submit></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
<br>
<html:form action="/system/service/faq">
<html:hidden property="action" value="" />
<!-- Options -->
<div>
	<html:button property="button" styleClass="button" onclick="doAction(this.form, 'action', 'create')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.add" bundle="messages" /></html:button>
	<html:button property="button" styleClass="button" onclick="doAction(this.form, 'action', 'delete')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.delete" bundle="messages" /></html:button>
</div>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
		<th width="30">#</th>
		<th width="30"><input type="checkbox" name="checkAll" onClick="setCheckBoxValue(this.form, 'codes', this.checked)"></th>
		<th><bean:message key="label.question" bundle="messages" /></th>
		<th width="150"><bean:message key="label.enabled" bundle="messages" /></th>
	</tr>
	<c:forEach var="item" items="${faqList}" varStatus="status">
		<c:url value="/system/service/faq.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
		</c:url>
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td align="center"><html:checkbox property="codes" value="${item.code}" /></td>
			<td><a href="${urlRead}" class="item">${item.question}</a></td>
			<td>
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
</html:form>