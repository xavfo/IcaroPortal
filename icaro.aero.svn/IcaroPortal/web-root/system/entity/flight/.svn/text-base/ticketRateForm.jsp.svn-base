<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<script language="JavaScript" type="text/javascript">
<!--
	function checkTax3Otion(element) {
		//alert(element.options[element.selectedIndex].value);
		var row = document.getElementById("tax3Option");
		if (element.value == 1 ) {
			row.style.display= isIE()?'block':'table-row';
		} else {
			row.style.display= 'none';
			/*document.forms[0].sellerCode.value= null;*/
		}
		/*var seller = document.forms[0].sellerCode.options[document.forms[0].sellerCode.selectedIndex].value;
		alert (seller);*/
	}

// -->
</script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<c:set var="systemMenuLabel" ><bean:message key="icaro.flight.ticketRate" bundle="systemMenu" /></c:set>
			${systemMenuLabel}
		</td>
		<td align="right">
			<bean:message key="icaro" bundle="systemMenu" /> &gt;
			<bean:message key="icaro.flight" bundle="systemMenu" /> &gt;
			${systemMenuLabel}
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<ul>
			<html:messages id="message" message="true">
				<li class="message"><c:out value="${message}" escapeXml="false" /></li>
			</html:messages>
			</ul>
			<div class="error"><html:errors/></div>			
		</td>
	</tr>
</table>

<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">
<table>
	<tr>
		<td class="subtitle">
			<c:if test="${!empty routeForm.code && routeForm.code != 0 && !empty routeForm.name}">
				<bean:message key="label.route" bundle="messages" />: ${routeForm.name}
			</c:if>
		</td>	
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.route">
			<tiles:put name="code" value="${routeForm.code}" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>
		<table class="tabForm" border="0" cellpadding="0" cellspacing="4" width="100%">		
		<tr>
			<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0"></td>
		</tr>
		<tr>
		<td>		
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
						<html:form action="/system/portal/route/ticketRate/save" focus="paxTypeCode" onsubmit="return preValidate(this)">
						<html:hidden property="action" value="save" />
						<html:hidden property="code" />
						<html:hidden property="applyTax3" />
						<html:hidden property="routeCode" value="${routeForm.code}" />
						<html:hidden property="tab" value="${param.tab}" />
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
						</tr>
						</table>
						<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td class="subtitle">
									<c:choose>
										<c:when test="${!empty ticketRateForm.code && ticketRateForm.code != 0}">
											<bean:message key="message.edit" bundle="systemHelp" arg0="${ticketRateForm.code}" />
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
								<td class="required">* <bean:message key="prompt.paxType" /> :</td>
								<td>
									<html:select property="paxTypeCode" size="1" styleClass="input" style="width: 200px;">
										<html:option value=""><bean:message key="option.select" bundle="messages" /></html:option>
										<html:options collection="paxTypeList" property="code" labelProperty="name"/>
									</html:select>
								
								</td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.enabled" /> :</td>
								<td>
									<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
									<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
								</td>										
							</tr>	
							<tr>
								<td class="required">* <bean:message key="prompt.specialFare" /> :</td>
								<td>
									<html:radio property="specialFare" value="true" /> <bean:message key="label.yes" bundle="messages" />
									<html:radio property="specialFare" value="false" /> <bean:message key="label.no" bundle="messages" />
								</td>										
							</tr>	
							<tr>
								<td class="required">* <bean:message key="prompt.fare" /> :</td>
								<td><html:text property="fare" styleClass="input" style="width: 80px;" maxlength="13" onchange="ticketRateForm.ivaRate.value=this.value*${ivaRate};" /></td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.ivaRate" /> :</td>
								<td><html:text property="ivaRate" styleClass="input" style="width: 80px;" maxlength="13" readonly="true" /></td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.tax1" /> :</td>
								<td><html:text property="tax1" styleClass="input" style="width: 80px;" maxlength="13" /></td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.tax2" /> :</td>
								<td><html:text property="tax2" styleClass="input" style="width: 80px;" maxlength="13" /></td>
							</tr>
							<tr>
								<td class="required">* <bean:message key="prompt.applyTax3" /> :</td>
								<td>
									<html:radio property="applyTax3Int" value="1"  onclick="checkTax3Otion(this)"   /> <bean:message key="label.yes" bundle="messages" />
									<html:radio property="applyTax3Int" value="0"  onclick="checkTax3Otion(this)" /> <bean:message key="label.no" bundle="messages" />
								</td>										
							</tr>	
							<c:choose>
								<c:when test="${ ticketRateForm.applyTax3 }">
									<tr id="tax3Option" style="display: table-row;">
								</c:when>
								<c:otherwise>
									<tr id="tax3Option" style="display: none;">
								</c:otherwise>
							</c:choose>
										<td class="required">* <bean:message key="prompt.tax3" /> :</td>
										<td><html:text property="tax3" styleClass="input" style="width: 80px;" maxlength="13" /></td>
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
						<html:javascript formName="ticketRateForm" dynamicJavascript="true" staticJavascript="false" />					
					</td>
				</tr>
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
				</tr>
			</table>	
		</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td background="${pageContext.request.contextPath}/images/tables/file_bottom.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="7" border="0" alt=""></td>
</tr>
<tr>
	<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
</tr>
</table>
<script language="JavaScript1.2">

	function preValidate (frm) { 
		
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty ticketRateForm.code && ticketRateForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el item de ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo item de ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateTicketRateForm(frm);
	  } else { return false; }
	}
	

</script>


