<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<%--<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.document" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.document" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>--%>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.portal.document" bundle="systemHelp" />
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<c:url value="/system/portal/categoryDocument.do" var="urlBack">
	<c:param name="action" value="list" />
	<c:param name="categoryCode" value="${category.code}" />
</c:url>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td align="right"><bean:message key="label.backTo" bundle="messages" />:<a href="${urlBack}" class="back"> <bean:message key="label.documentList" bundle="messages" /></a></td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<bean:message key="message.categoryDocument" bundle="systemHelp" arg0="${category.name}" />
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty documentForm.code && documentForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${documentForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/portal/categoryDocument/save" focus="name" onsubmit="return validateDocumentForm(this);">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="categoryCode" />
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td height="30" width="170"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 250px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.file" /> :</td>
		<td>
			<html:text property="file" styleClass="input" style="width: 250px;" />
			<input type="button" name="btnFile" value="..." style="width: 20px" onClick="openUploadWindow('/popup/openUploadFile.do', '/files', 'documentForm', 'file','documentForm.file',0)">			
		</td>
	</tr>	
	<tr>
		<td class="required">* <bean:message key="prompt.documentType" /> :</td>
		<td>
			<html:select property="documentTypeCode" size="1" styleClass="input" style="width: 250px;">
				<html:options collection="documentTypeList" property="code" labelProperty="name"/>
			</html:select>
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
<html:javascript formName="documentForm" dynamicJavascript="true" staticJavascript="false" />
