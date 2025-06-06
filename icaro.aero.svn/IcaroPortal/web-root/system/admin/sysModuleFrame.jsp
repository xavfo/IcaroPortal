<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<c:url value="/system/admin/sysModule.do" var="urlAdd">
    <c:param name="action" value="create" />
</c:url>
<c:url value="/system/admin/sysModule.do" var="urlList">
    <c:param name="action" value="list" />
</c:url>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="administration.module" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="administration" bundle="systemMenu" /> >
			<bean:message key="administration.module" bundle="systemMenu" />
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
		<td width="250" valign="top"><iframe id="itree" name="itree" width="250" height="450" src="${urlList}" scrolling="Auto" frameborder="0" style="border: 1px solid #999999;"></iframe></td>
		<td width="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="1" border="0"></td>
		<td><iframe id="iform" name="iform" width="100%" height="450" src="${urlAdd}" frameborder="0" style="border: 1px solid #999999;"></iframe></td>
	</tr>
</table>