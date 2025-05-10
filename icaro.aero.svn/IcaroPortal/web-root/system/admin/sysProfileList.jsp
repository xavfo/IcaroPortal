<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="administration.role" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="administration" bundle="systemMenu" /> >
			<bean:message key="administration.role" bundle="systemMenu" />
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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<bean:message key="message.profile" bundle="systemHelp" arg0="${sysRole.name}" />
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>


<html:form action="/system/admin/sysProfile">
<html:hidden property="action" value="save" />
<html:hidden property="roleCode" value="${sysRole.code}" />
<!-- Options -->
<div>
	<%--<c:if test="${sysRole.code != 1}">--%>
		<html:submit style="width: 80px;" styleClass="button" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.ok" bundle="messages" /></html:submit>
	<%--</c:if>--%>
	<html:cancel styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.cancel" bundle="messages" /></html:cancel>
</div>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<!-- List data -->
<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<c:forEach var="item" items="${sysModuleList}" varStatus="status">
		<tr>
			<c:if test="${item.level == 1}">
				<td bgcolor="#F0F0F0">
					<html:multibox property="codes" value="${item.code}" /> ${item.name}
				</td>
			</c:if>
			<c:if test="${item.level != 1}">
				<td style="padding-left: ${(item.level - 1) * 40}px;">
					<html:multibox property="codes" value="${item.code}" /> ${item.name}
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>

<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>
<div>
	<%--<c:if test="${sysRole.code != 1}">--%>
		<html:submit style="width: 80px;" styleClass="button" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.ok" bundle="messages" /></html:submit>
	<%--</c:if>--%>
	<html:cancel styleClass="button" style="width: 80px;" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.cancel" bundle="messages" /></html:cancel>
</div>

</html:form>