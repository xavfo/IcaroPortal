<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<script language="javascript">
function send(frm, action){
	if ( typeof(action) == 'undefined' )
		action="save";
	frm.action.value=action;
	var button =  document.getElementById('inputSubmit');
	button.click();
}
function cancel(frm) {
	frm.action.value="save";
	var button =  document.getElementById('inputCancel');
	button.click();
}
</script>
<span style="display:none"><html:submit styleId="inputSubmit" ><bean:message key="button.ok" bundle="messages" /></html:submit></span>
<span style="display:none"><html:cancel styleId="inputCancel"><bean:message key="button.cancel" bundle="messages" /></html:cancel></span>
<span style="display:none"><html:reset styleId="inputReset" ><bean:message key="button.reset" bundle="messages" /></html:reset></span>
<table border="0" cellpadding="2" cellspacing="0">
	<tr>
		<c:if test="${navBarConfig.add}">
			<td align="center"><a href="javascript: doAction(document.forms[${navBarConfig.formIndex}], 'action', 'create')"><img src="${pageContext.request.contextPath}/images/system/icons/anadir.gif" alt="<bean:message key="button.add" bundle="messages" />" width="26" height="27" border="0" /><br /></a>	</td>
		</c:if>
		<c:if test="${navBarConfig.edit}">
			<td align="center"><a href="javascript: doActionN(document.forms[${navBarConfig.formIndex}], 'action', 'read', 'codes')"><img src="${pageContext.request.contextPath}/images/system/icons/editar.gif" alt="<bean:message key="button.edit" bundle="messages" />" width="26" height="27"  border="0"/></a></td>
		</c:if>
		<c:if test="${navBarConfig.delete}">
			<td align="center"><a href="javascript: doActionN(document.forms[${navBarConfig.formIndex}], 'action', 'delete', 'codes')"><img src="${pageContext.request.contextPath}/images/system/icons/eliminar.gif" alt="<bean:message key="button.delete" bundle="messages" />" width="26" height="27" border="0" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.save}">
			<td align="center"><a href="javascript: send(document.forms[${navBarConfig.formIndex}]);"><img src="${pageContext.request.contextPath}/images/system/icons/guardar.gif" alt="<bean:message key="button.save" bundle="messages" />" width="26" height="27" border="0" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.apply}">
			<td align="center"><a href="javascript: send(document.forms[${navBarConfig.formIndex}], 'apply')"><img src="${pageContext.request.contextPath}/images/system/icons/aplicar.gif" alt="<bean:message key="button.apply" bundle="messages" />" width="26" height="27" border="0" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.reset}">
			<td align="center"><a href="javascript: document.forms[${navBarConfig.formIndex}].inputReset.click(); "><img src="${pageContext.request.contextPath}/images/system/icons/restaurar.gif" alt="<bean:message key="button.reset" bundle="messages" />" width="26" height="27" border="0" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.preview}">
			<td align="center"><a href="javascript: ;"><img src="${pageContext.request.contextPath}/images/system/icons/vista_previa.gif" alt="<bean:message key="button.preview" bundle="messages" />" width="26" height="27" border="0" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.assign}">
			<td align="center"><a href="javascript: send(document.forms[${navBarConfig.formIndex}], 'assign')"><img src="${pageContext.request.contextPath}/images/system/icons/guardar.gif" alt="<bean:message key="button.assign" bundle="messages" />" width="26" height="27" border="0" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.cancel}">
			<td align="center"><a href="javascript: cancel(document.forms[${navBarConfig.formIndex}]);"><img src="${pageContext.request.contextPath}/images/system/icons/cancelar.gif" alt="<bean:message key="button.cancel" bundle="messages" />" width="26" height="27" border="0" /></a></td>
		</c:if>
	</tr>
	<tr>
		<c:if test="${navBarConfig.add}">
			<td align="center"><a href="javascript: doActionN(document.forms[${navBarConfig.formIndex}], 'action', 'create')"><bean:message key="button.add" bundle="messages" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.edit}">
			<td align="center"><a href="javascript: doActionN(document.forms[${navBarConfig.formIndex}], 'action', 'read', 'codes')"><bean:message key="button.edit" bundle="messages" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.delete}">
			<td align="center"><a href="javascript: doActionN(document.forms[${navBarConfig.formIndex}], 'action', 'delete', 'codes')"><bean:message key="button.delete" bundle="messages" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.save}">
			<td align="center"><a href="javascript: send(document.forms[${navBarConfig.formIndex}]);"><bean:message key="button.save" bundle="messages" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.apply}">
			<td align="center"><a href="javascript: send(document.forms[${navBarConfig.formIndex}], 'apply')"><bean:message key="button.apply" bundle="messages" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.reset}">
			<td align="center"><a href="javascript: document.forms[${navBarConfig.formIndex}].inputReset.click();"><bean:message key="button.reset" bundle="messages" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.preview}">
			<td align="center"><a href="javascript: ;"><bean:message key="button.preview" bundle="messages" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.assign}">
			<td align="center"><a href="javascript: send(document.forms[${navBarConfig.formIndex}], 'assign');"><bean:message key="button.assign" bundle="messages" /></a></td>
		</c:if>
		<c:if test="${navBarConfig.cancel}">
			<td align="center"><a href="javascript: cancel(document.forms[${navBarConfig.formIndex}]);"><bean:message key="button.cancel" bundle="messages" /></a></td>
		</c:if>
	</tr>
</table>