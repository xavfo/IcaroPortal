<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<%--<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="portal.sectionRelation" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="portal" bundle="systemMenu" /> >
			<bean:message key="portal.sectionRelation" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>--%>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.portal.sectionRelation" bundle="systemHelp" />
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
			<bean:message key="message.sectionRelated" bundle="systemHelp" arg0="${section.name}" />
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/portal/sectionRelation">
<html:hidden property="action" value="" />
<html:hidden property="sectionCode" />

<table align="center" border="0" cellpadding="4" cellspacing="4" class="form" width="95%">
    <tr>
        <td class="field" width="45%">&nbsp;<bean:message key="label.available" bundle="messages" /></td>
        <td class="field">&nbsp;</td>
        <td class="field" width="45%">&nbsp;<bean:message key="label.related" bundle="messages" /></td>
    </tr>
    <tr>
        <td>
			<html:select property="sectCodes" multiple="true" size="25" styleClass="input" style="width: 100%;">
				<html:options collection="availableList" property="code" labelProperty="name"/>
			</html:select>			
        </td>
        <td align="center">
			<html:button property="button" styleClass="button" style="width: 90px;" onclick="doAction(this.form, 'action', 'add')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')">&gt;&gt;&nbsp;&nbsp;<bean:message key="button.add2" bundle="messages" /></html:button>
            &nbsp;<br><br>
            <html:button property="button" styleClass="button" style="width: 90px;" onclick="doAction(this.form, 'action', 'remove')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')">&lt;&lt;&nbsp;&nbsp;<bean:message key="button.remove" bundle="messages" /></html:button>
        </td>
        <td>
			<html:select property="codes" multiple="true" size="25" styleClass="input" style="width: 100%;">
				<html:options collection="assignedList" property="code" labelProperty="related.name"/>
			</html:select>
        </td>
    </tr>
</table>
</html:form>