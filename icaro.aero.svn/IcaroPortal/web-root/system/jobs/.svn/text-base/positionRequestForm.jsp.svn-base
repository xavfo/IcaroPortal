<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="information.jobs.positionRequest" bundle="systemMenu" />
		</td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			<bean:message key="information.catalogues" bundle="systemMenu" /> >	
			<bean:message key="information.jobs.area" bundle="systemMenu" /> >
			<bean:message key="information.jobs.positionRequest" bundle="systemMenu" />	
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<html:form action="/system/jobs/positionRequest/save" focus="name" onsubmit="return preValidate(this)">
<table border="0" cellpadding="4" cellspacing="0" width="100%">
<tr>
  <td width="85%">&nbsp;</td>
  <td align="center"><a href="javascript: doActionN(document.positionRequestForm, 'action', 'save')"><img src="${pageContext.request.contextPath}/images/system/icons/guardar.gif" alt="Save" width="26" height="27" border="0" /></a></td>
  <td align="center"><a href="javascript: doActionN(document.positionRequestForm, 'action', 'apply')"><img src="${pageContext.request.contextPath}/images/system/icons/aplicar.gif" alt="Apply" width="26" height="27" border="0" /></a></td>
  <td align="center"><a onclick="document.positionRequestForm.reset();return false;" href="#"><img src="${pageContext.request.contextPath}/images/system/icons/restaurar.gif" alt="Reset" width="26" height="27" border="0" /></a></td>
  <td align="center"><a href="javascript: doActionN(document.positionRequestForm, 'action', 'list')"><img src="${pageContext.request.contextPath}/images/system/icons/cancelar.gif" alt="Cancel" width="26" height="27" border="0" /></a></td>
<tr>
	<td width="85%">&nbsp;</td>
    <td align="center"><a href="javascript: doActionN(document.positionRequestForm, 'action', 'save')"><bean:message key="button.save" bundle="messages" /></a></td>
	<td align="center"><a href="javascript: doActionN(document.positionRequestForm, 'action', 'apply')">Apply</a></td>
    <td align="center"><a onclick="document.positionRequestForm.reset();return false;" href="#"><bean:message key="button.reset" bundle="messages" /></a></td>
    <td align="center"><a href="javascript: doActionN(document.positionRequestForm, 'action', 'list')">Cancelar</a></td>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty catalogueForm.code && catalogueForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${catalogueForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="5" border="0"><br>
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
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="15" border="0"><br>


<html:hidden property="orderField" value="name" />
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="areaCode" />
<table align="right" border="0" cellpadding="4" cellspacing="0" width="100%">
	<tr>
		<td colspan="2">code:${positionForm.areaCode}
		<tiles:insert definition="tabs.positions">
			<tiles:put name="code" value="${positionForm.areaCode}" />
		</tiles:insert>
		</td>
	</tr>
	<tr>
		<td class="required" width="120">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required" width="120">* <bean:message key="prompt.shortDescription" /> :</td>
		<td><html:text property="shortDescription" styleClass="input" style="width: 200px;" /></td>
	</tr>	
	<tr>
		<td class="optional" width="120">* <bean:message key="prompt.longDescription" /> :</td>
		<td><html:text property="longDescription" styleClass="input" style="width: 200px;" /></td>
	</tr>	
	<tr>
		<td class="optional" width="120">* <bean:message key="prompt.profile" /> :</td>
		<td><html:text property="profileDescription" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.enabled" /> :</td>
		<td>
			<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr>	
	<tr>
		<td height="30"></td>
		<td class="notes"><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
	</tr>	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
	</tr>
</table>
</html:form>
<html:javascript formName="catalogueForm" dynamicJavascript="true" staticJavascript="false" />
<script language="JavaScript1.2">
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty catalogueForm.code && catalogueForm.code != 0}">
			var msg = 'Se actualizar\u00E1 la categor\u00EDa ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear una nueva categor\u00EDa.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateCatalogueForm(frm);
	  } else { return false; }
	}
</script>