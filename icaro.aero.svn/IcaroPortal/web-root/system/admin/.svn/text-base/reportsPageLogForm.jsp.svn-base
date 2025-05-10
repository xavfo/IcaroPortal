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
		<td class="title" width="75%"><bean:message key="audit.reports" bundle="systemMenu" />
		 de <bean:message key="${pageLog.resourceType}"/> "${pageLog.resourceDescription}"<BR>
		<c:if test="${!empty(pageLog.toDate) && !empty(pageLog.fromDate)}">
			desde ${pageLog.fromDate} hasta ${pageLog.toDate}
		</c:if>
		</td>
		<td align="right">
			<bean:message key="audit" bundle="systemMenu" /> >
			<bean:message key="audit.reports" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>


<html:form action="/system/admin/reportsPageLog">
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
<%--<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			Información Auditoría
		</td>
	</tr>
</table>--%>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table class="list" border="0" cellpadding="2" cellspacing="1" width="100%">
	<tr>
	<tr>
		<th width="4%">#</th>
		<th width="24%">Fecha</th>
		<th width="24%">Secci&oacute;nId</th>
		<th width="24%">Direcci&oacute;n Remota</th>
		<th width="24%">Url</th>
	</tr>
	<c:forEach var="item" items="${pageLogList}" varStatus="status">
		<c:url value="/system/admin/pageLog.do" var="urlRead">
			<c:param name="action" value="read" />
			<c:param name="code" value="${item.code}" />
			<c:param name="isReport" value="true" />
			<c:param name="fromDate" value="${pageLog.fromDate}" />
			<c:param name="toDate" value="${pageLog.toDate}" />
		</c:url>
		<tr class="item" onMouseOver="swapClass(this, 'item', 'itemhi')" onMouseOut="swapClass(this, 'item', 'itemhi')">
			<td>${status.count}</td>
			<td><%--<a href="${urlRead}" class="item">--%><fmt:formatDate value="${item.date.time}" pattern="${initParameters.DATE_TIME_FORMAT}"/><%--</a>--%></td>
			<td>${item.sessionId}</td>
			<td>${item.remoteAddress}</td>
			<td>${item.url}</td>
		</tr>
	</c:forEach>
</table>
</html:form>