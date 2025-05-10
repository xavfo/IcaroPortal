<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="document.document" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="document.document" bundle="systemMenu" />
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<bean:message key="dialog.portal.document" bundle="systemHelp" />
			<html:messages id="message" message="true">
				<div class="message"><c:out value="${message}" escapeXml="false" /></div>
			</html:messages>
		</td>
		<td align="right">
			<c:url value="/system/container/support.do" var="urlBack">
				<c:param name="action" value="list" />
			</c:url>
			<a href="${urlBack}" class="back"><bean:message key="label.listAll" bundle="messages" /> <bean:message key="event.event" bundle="systemMenu" /></a>
		</td>			
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty supportForm.code && supportForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${supportForm.title}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/container/support/save" onsubmit="return validateEventForm(this);">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="languageCode" value="${appLanguage.code}" />
<!--Options-->
<table border="0" cellpadding="4" cellspacing="0" width="100%">
<tr>
  <td width="85%">&nbsp;</td>
  <td align="center"><a href="javascript: doActionN(document.supportContainerForm, 'action', 'save')"><img src="${pageContext.request.contextPath}/images/system/icons/guardar.gif" alt="Save" width="26" height="27" border="0" /></a></td>
  <td align="center"><a href="javascript: doActionN(document.supportContainerForm, 'action', 'apply')"><img src="${pageContext.request.contextPath}/images/system/icons/aplicar.gif" alt="Apply" width="26" height="27" border="0" /></a></td>
  <td align="center"><a onclick="document.supportContainerForm.reset();return false;" href="#"><img src="${pageContext.request.contextPath}/images/system/icons/restaurar.gif" alt="Reset" width="26" height="27" border="0" /></a></td>
  <td align="center"><a href="javascript: doActionN(document.supportContainerForm, 'action', 'list')"><img src="${pageContext.request.contextPath}/images/system/icons/cancelar.gif" alt="Cancel" width="26" height="27" border="0" /></a></td>
<tr>
	<td width="85%">&nbsp;</td>
    <td align="center"><a href="javascript: doActionN(document.supportContainerForm, 'action', 'save')"><bean:message key="button.save" bundle="messages" /></a></td>
	<td align="center"><a href="javascript: doActionN(document.supportContainerForm, 'action', 'apply')">Apply</a></td>
    <td align="center"><a onclick="document.supportContainerForm.reset();return false;" href="#"><bean:message key="button.reset" bundle="messages" /></a></td>
    <td align="center"><a href="javascript: doActionN(document.supportContainerForm, 'action', 'list')">Cancelar</a></td>
</table>
<!--fin options-->
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td height="30" width="170"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.title" /> :</td>
		<td><html:text property="title" styleClass="input" style="width: 200px;" /></td>
	</tr>			
	<tr>
		<td class="required"><bean:message key="prompt.leadinText" /> :</td>
		<td>
			<html:textarea property="shortDescription" cols="40" rows="3" />	
		</td>
	</tr>		
	<tr>
		<td class="optional"> <bean:message key="prompt.keyword" /> :</td>
		<td>
			<html:textarea property="keyWords" styleId="leadinText" cols="40" rows="3" />	
		</td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.documentType" /> :</td>
		<td>
			<html:select property="docTypeCode" size="1" styleClass="input" style="width: 250px;">
				<html:options collection="supportTypeList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.category" /> :</td>
		<td>
			<html:select property="categoryCode" size="1" styleClass="input" style="width: 250px;">
				<html:options collection="supportCategoryList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr>	
	<tr>
		<td class="required">*<bean:message key="prompt.document" />:</td>
		<td>
			<html:text property="path" styleClass="input" style="width: 250px;" />
			<!--a href="${pageContext.request.contextPath}${item.path}" target="_blank"><img src="<%= request.getContextPath() %>/images/icons/find.gif" width="16" height="16" border="0" align="absmiddle" alt="Ver Documento"></a-->
			<input type="button" name="btnDate" value="..." style="width: 20px" onClick="openUploadWindow('/popup/openUploadFile.do', '/documentos/compartido/', 'supportContainerForm', 'path','supportContainerForm.path',0,'isDocContainer')">
		</td>
	</tr>		
	<tr>
		<td class="optional">* <bean:message key="prompt.from" /> :</td>
		<td><html:text property="fromDate" styleId="fromDate" style="width: 100px;" /> <input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;"></td>
	</tr>
	<tr>
		<td class="optional">* <bean:message key="prompt.to" /> :</td>
		<td><html:text property="toDate" styleId="toDate" style="width: 100px;" /> <input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;"></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.enabled" /></td>
		<td>
			<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
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
<html:javascript formName="supportForm" dynamicJavascript="true" staticJavascript="false" />
<script language="JavaScript1.2">
	Calendar.setup(
		{
			inputField  	: "fromDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "fromBtn"
		}
	);
	Calendar.setup(
		{
			inputField  	: "toDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "toBtn"
		}
	);
</script>