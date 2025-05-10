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
		<td align="right">
			<c:url value="/system/portal/language.do" var="urlBack">
				<c:param name="action" value="list" />
			</c:url>
			<a href="${urlBack}" class="back"><bean:message key="label.listAll" bundle="messages" /> <bean:message key="portal.language" bundle="systemMenu" /></a>
		</td>			
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty languageForm.code && languageForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${languageForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/portal/language/save" focus="name" onsubmit="return validateLanguageForm(this);">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="default" />
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td height="30" width="170"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.enabled" /> :</td>
		<td>
			<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.iso" /> :</td>
		<td><html:text property="localeLang" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.integerFormat" /> :</td>
		<td><html:text property="integerFormat" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.floatFormat" /> :</td>
		<td><html:text property="floatFormat" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.currencyFormat" /> :</td>
		<td><html:text property="currencyFormat" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.dateFormat" /> :</td>
		<td><html:text property="dateFormat" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.timeFormat" /> :</td>
		<td><html:text property="timeFormat" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.dateTimeFormat" /> :</td>
		<td><html:text property="dateTimeFormat" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.shortDateFormat" /> :</td>
		<td><html:text property="shortDateFormat" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.longDateFormat" /> :</td>
		<td><html:text property="longDateFormat" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.charset" /> :</td>
		<td><html:text property="charset" styleClass="input" style="width: 200px;" /></td>
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
<html:javascript formName="languageForm" dynamicJavascript="true" staticJavascript="false" />
