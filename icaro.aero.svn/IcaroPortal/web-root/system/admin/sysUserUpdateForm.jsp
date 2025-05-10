<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="administration.user" bundle="systemMenu" /></td>
		<td align="right">
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.administration.user" bundle="systemHelp" />
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
			<c:choose>
				<c:when test="${!empty sysUserUpdateForm.code && sysUserUpdateForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${sysUserUpdateForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/update/sysUser/save" focus="firstName" onsubmit="return validateSysUserUpdateForm(this);">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="roleCode" />
<%--html:hidden property="sellerCode" /--%>
<html:hidden property="name" />
<html:hidden property="enabled" />
<html:hidden property="accessModeCode" />
<html:hidden property="reset" value="true"/>


<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td height="30" width="120"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.firstName" /> :</td>
		<td><html:text property="firstName" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.lastName" /> :</td>
		<td><html:text property="lastName" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.password" /> :</td>
		<td><html:password property="password" styleClass="input" style="width: 200px;" value="" maxlength="50"/></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.passwordVerify" /> :</td>
		<td><html:password property="confirmPassword" styleClass="input" style="width: 200px;" value="" maxlength="50"/></td>
	</tr>	
	<tr>
		<td class="required">* <bean:message key="prompt.email" /> :</td>
		<td><html:text property="email" styleClass="input" style="width: 200px;" /></td>
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
<html:javascript formName="sysUserUpdateForm" dynamicJavascript="true" staticJavascript="false" />