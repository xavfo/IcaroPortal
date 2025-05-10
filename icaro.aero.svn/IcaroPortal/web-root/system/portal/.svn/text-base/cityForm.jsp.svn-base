<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/Ajax.js"></script>
<script language="JavaScript1.2">
<%--alert (${cityForm.stateCode});--%>
<!--	
	CONTEXT_PATH = '${pageContext.request.contextPath}';		
	
	/*
	var aStates = [	
		<c:forEach var="item" items="${countryList}">
			[<c:out value="${item.code}" />, 0, '<bean:message key="option.none" bundle="messages" />'],
		</c:forEach>
		<c:forEach var="item" items="${stateList}">
			[<c:out value="${item.parentCode}" />, <c:out value="${item.code}" />, '<c:out value="${item.name}" escapeXml="false"/>'],				
		</c:forEach>
		[0, 0, '<bean:message key="option.none" bundle="messages" />']
	];
	*/
		
	/*
	function setState() {
		syncSelect(document.forms[0].stateCode, document.forms[0].countryCode.value, aStates);	
	}	
		
	function initPage() {		
		setState();
	}
	*/
//-->
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="information.city" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="information.city" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			${systemMenuLabel}
		</td>
	</tr>
</table>

<hr width="100%" size="1" color="#999999" noshade>
<html:form action="/system/portal/city/save" focus="name" onsubmit="return preValidate(this)">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="key" />
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
		<div class="error"><html:errors/></div>	
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty cityForm.code && cityForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${cityForm.name}" />
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
		<td class="required">* <bean:message key="prompt.country" /> :</td>
		<td>
			<%--
			<html:select property="countryCode" size="1" styleClass="input" style="width: 250px;" onchange="setState()">
				<html:options collection="countryList" property="code" labelProperty="name"/>
			</html:select>
			--%>
			<html:select property="countryCode" size="1" styleClass="input" style="width: 250px;" onchange="retrieveURL(CONTEXT_PATH + '/system/portal/city.do?ask=COMMAND_NAME_1&action=listStates',this.form);">
				<html:options collection="countryList" property="code" labelProperty="name"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.state" />:</td>
		<td>
			<span id="applications" class="normal" >
			<html:select property="stateCode" size="1" styleClass="input" style="width: 250px;" >
				<c:if test="${!empty listOfOptions}">
					<html:options collection="listOfOptions" property="code" labelProperty="name"/>
				</c:if>
			</html:select>
			</span>
		
		</td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.enabled" /> :</td>
		<td>
			<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr>	
	<!--tr>
		<td class="optional"><bean:message key="prompt.homeDelivery" /> :</td>
		<td>
			<html:radio property="homeDelivery" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="homeDelivery" value="false" /> <bean:message key="label.no" bundle="messages" />
		</td>
	</tr-->	

	<tr>
		<td height="30" width="170"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
</table>

</html:form>
<html:javascript formName="cityForm" dynamicJavascript="true" staticJavascript="false" />
	
<script language="JavaScript1.2">
	function initPage() {		
		retrieveURL(CONTEXT_PATH + '/system/portal/city.do?ask=COMMAND_NAME_1&action=listStates',document.cityForm)

	}

	function preValidate (frm) { 
		
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty cityForm.code && cityForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el item de ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo item de ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateCityForm(frm);
	  } else { return false; }
	}
	
	//initPage();
</script>
