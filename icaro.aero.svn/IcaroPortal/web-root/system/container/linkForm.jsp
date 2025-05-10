<%@ page language="java" %>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>


<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="service.link" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="service.link" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			<bean:message key="container.container" bundle="systemMenu" /> >
			${systemMenuLabel}
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>

<html:form action="/system/container/link/save" focus="title" onsubmit="return preValidate(this)">
<html:hidden property="action" value="save" />
<html:hidden property="code" />

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
			<ul>
			<html:messages id="message" message="true">
				<li class="message"><c:out value="${message}" escapeXml="false" /></li>
			</html:messages>
			<ul>
			<div class="error"><html:errors/></div>		
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty linkContainerForm.code && linkContainerForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${linkContainerForm.title}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.title" /> :</td>
		<td><html:textarea property="title"  style="width: 350px; height: 80px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.url" /> :</td>
		<td><html:text property="url" styleClass="input" style="width: 350px;" /></td>
	</tr>
	<tr>
		<td class="required">*<bean:message key="prompt.category" /> :</td>
		<td>
			<html:select property="categoryCode" size="1" styleClass="input" style="width: 250px;">
				<html:options collection="linkCategoryList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="required">*<bean:message key="prompt.type" /> :</td>
		<td>
			<html:select property="typeCode" size="1" styleClass="input" style="width: 250px;">
				<html:options collection="linkTypeList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.description" /> :</td>
		<td><html:textarea property="description"  style="width: 350px; height: 80px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.enabled" /></td>
		<td>
			<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>		
	</tr>
	<tr>
		<td class="optional"> <bean:message key="prompt.orderIndex" /> :</td>
		<td><html:text property="index" style="width: 250px;" /></td>
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
<html:javascript formName="linkContainerForm" dynamicJavascript="true" staticJavascript="false" />

<script language="JavaScript1.2">
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty linkContainerForm.code && linkContainerForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateLinkContainerForm(frm);
	  } else { return false; }
	}
</script>
