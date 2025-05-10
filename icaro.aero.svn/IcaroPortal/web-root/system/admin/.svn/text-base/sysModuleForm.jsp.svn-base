<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<script language="JavaScript">
<!--
	<c:url value="/system/admin/sysModule.do" var="urlList">
   		<c:param name="action" value="list" />
	</c:url>
	function refreshTree() {
		if ( document.all )
			window.top.document.all.itree.src = '${urlList}';
		else
			window.top.document.getElementById("itree").src = '${urlList}';
	}
//-->
</script>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		<html:messages id="message" message="true">
			<div class="message"><c:out value="${message}" escapeXml="false" /></div>
		</html:messages>
		<div class="error"><html:errors/></div>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty sysModuleForm.code && sysModuleForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${sysModuleForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/admin/sysModule/save" focus="name" onsubmit="return validateSysModuleForm(this);">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td height="30" width="120"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" style="width: 250px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.parent" /> :</td>
		<td>
			<html:select property="parentCode" size="1" styleClass="input" style="width: 250px;">
				<html:option value="0"><bean:message key="option.root" bundle="messages" /></html:option>
				<html:options collection="sysModuleParentList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.group" /> :</td>
		<td>
			<html:radio property="group" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="group" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.key" /> :</td>
		<td><html:text property="key" style="width: 250px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.url" /> :</td>
		<td><html:text property="url" style="width: 250px;" /></td>
	</tr>
	<tr>
		<td class="optional">Modo de Acceso :</td>
		<td>
			<html:select property="accessModeCode" size="1" styleClass="input" style="width: 250px;">
				<html:options collection="sysAccessModeList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="optional">Mostrar en Panel :</td>
		<td>
			<html:radio property="showPanel" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="showPanel" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr>
	<tr>
		<td height="30"></td>
		<td class="subtitle"><bean:message key="label.menu" bundle="messages" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.orderIndex" /> :</td>
		<td><html:text property="orderIndex" style="width: 50px;" /></td>
	</tr>	
	<tr>
		<td class="optional"><bean:message key="prompt.x" /> :</td>
		<td><html:text property="x" style="width: 50px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.y" /> :</td>
		<td><html:text property="y" style="width: 50px;" /></td>
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
			<html:button property="button" styleClass="button" style="width: 80px;" onclick="doAction(this.form, 'action', 'delete')" onmouseover="swapClass(this, 'button', 'buttonhi')" onmouseout="swapClass(this, 'button', 'buttonhi')"><bean:message key="button.delete" bundle="messages" /></html:button>
		</td>
	</tr>
</table>
</html:form>
<html:javascript formName="sysModuleForm" dynamicJavascript="true" staticJavascript="false" />
<script language="JavaScript1.2">
<!--
refreshTree();
//-->
</script>