<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="information.jobs.position" bundle="systemMenu" />
		</td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="information.jobs" bundle="systemMenu" /> >	
			<bean:message key="information.jobs.area" bundle="systemMenu" /> >
			<bean:message key="information.jobs.position" bundle="systemMenu" />	
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<html:form action="/system/jobs/position/save" focus="name" onsubmit="return preValidate(this)">
<html:hidden property="orderField" value="name" />
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="areaCode" value="${positionForm.areaCode}"/>

<table border="0" cellpadding="4" cellspacing="0" width="100%">
<tr>
  <td align="right">
  	<tiles:insert definition="system.navBar">
		<tiles:put name="save" 		value="true" />
		<tiles:put name="apply"		value="true" />
		<tiles:put name="reset" 	value="true" />
		<tiles:put name="cancel" 	value="true" />
	</tiles:insert>
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
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty positionForm.code && positionForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${positionForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<%--
<table align="right" border="0" cellpadding="4" cellspacing="0" width="100%">
	<tr>
		<td colspan="2">
		<tiles:insert definition="tabs.positions">
			<tiles:put name="code" value="${positionForm.areaCode}" />
			<tiles:put name="tab" value="2" />
		</tiles:insert>
		</td>
	</tr>
	<tr>
		<td class="required" width="120">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required" width="120">* <bean:message key="prompt.brief.description" /> :</td>
		<td><html:text property="shortDescription" styleClass="input" style="width: 200px;" /></td>
	</tr>	
	<tr>
		<td class="optional" width="120">* <bean:message key="prompt.detailed.description" /> :</td>
		<td><html:text property="longDescription" styleClass="input" style="width: 200px;" /></td>
	</tr>	
	<tr>
		<td class="optional" width="120">* <bean:message key="prompt.profile.description" /> :</td>
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
--%>

<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
	<tr>
		<td colspan="2">
			<tiles:insert definition="tabs.positions">
				<tiles:put name="code" value="${positionForm.areaCode}" />
				<tiles:put name="tab" value="2" />
			</tiles:insert>
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.brief.description" /> :</td>
		<td><html:textarea property="shortDescription" cols="40" rows="3" /></td>
	</tr>	
	<tr>
		<td class="optional"> <bean:message key="prompt.detailed.description" /> :</td>
		<td><html:textarea property="longDescription" cols="40" rows="3" /></td>
	</tr>	
	<tr>
		<td class="optional"> <bean:message key="prompt.profile.description" /> :</td>
		<td><html:textarea property="profileDescription" cols="40" rows="3" /></td>
	</tr>
	
	<tr>
		<td class="optional"><bean:message key="prompt.enabled" /> :</td>
		<td>
			<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr>	
	<tr>
		<td height="30" width="170"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
</table>



</html:form>
<html:javascript formName="positionForm" dynamicJavascript="true" staticJavascript="false" />
<script language="JavaScript1.2">
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty positionForm.code && positionForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el cargo ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo cargo.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validatePositionForm(frm);
	  } else { return false; }
	}
</script>