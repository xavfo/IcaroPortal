<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">	
				<bean:message key="information.catalogues.airport" bundle="systemMenu" />
				<c:set var="systemMenuLabel" ><bean:message key="information.catalogues.airport" bundle="systemMenu" /></c:set>			
		</td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			<bean:message key="information.catalogues" bundle="systemMenu" /> >	
			${systemMenuLabel}
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>

<html:form action="/system/information/airport/save" focus="name" onsubmit="return preValidate(this)">
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
				<c:when test="${!empty airportForm.code && airportForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${airportForm.name}" />
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
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">*<bean:message key="prompt.iataCode" /> :</td>
		<td><html:text property="iataCode" styleClass="input"/></td>
	</tr>
	<tr>
		<td class="required">*<bean:message key="prompt.city" />:</td>
		<td>
		<span id="cityList" class="normal" >
		<html:select property="cityCode" size="1" styleClass="input" style="width: 250px;"  >
			<html:option value="">
				<bean:message key="option.none" bundle="messages" />
			</html:option>
		<c:if test="${!empty cityList}">
			<html:options collection="cityList" property="code" labelProperty="name"/>
		</c:if>
		</html:select>
		</span>
		</td>
	</tr>
	<tr>
		<td class="required"><bean:message key="prompt.enabled" /> :</td>
		<td>
			<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
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
<html:javascript formName="airportForm" dynamicJavascript="true" staticJavascript="false" />

<script language="JavaScript1.2">
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty airportForm.code && airportForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el(la) ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un(a) nuevo(a) ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateAirportForm(frm);
	  } else { return false; }
	}
</script>
