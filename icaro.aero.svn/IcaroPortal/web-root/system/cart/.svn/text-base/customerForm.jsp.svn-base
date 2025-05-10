<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="cart.customer" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="cart.customer" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="cart" bundle="systemMenu" /> &gt;
			${systemMenuLabel}
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
		<html:messages id="message" message="true">
			<div class="message"><c:out value="${message}" escapeXml="false" /></div>
		</html:messages>
		</td>
	</tr>
</table>

<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">
<table>
	<tr>
		<td class="subtitle">${customerForm.firstName} ${customerForm.lastName}</td>	
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.customer">
			<tiles:put name="code" value="${customerForm.code}" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>
		<html:form action="/system/cart/customer/save" focus="message" onsubmit="return preValidate(this)">
		<html:hidden property="action" value="save" />
		<html:hidden property="code" />
		<html:hidden property="firstName" />
		<html:hidden property="lastName" />
		<html:hidden property="email" />
		<html:hidden property="identity" />
		<html:hidden property="password" />
		<html:hidden property="gender" />
		<html:hidden property="suscribeBulletin" />
		<html:hidden property="birthdateDate" />
		<html:hidden property="identity" />
		<%--html:hidden property="mobile" /--%>
		<table class="tabForm" border="0" cellpadding="0" cellspacing="4" width="100%">		
		<tr>
			<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0"></td>
		</tr>
		<tr>
		<td>		
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
			            <table cellpadding="0" cellspacing="0" border="0" width="100%" >
						<tr>
						<td valign="top">
						
						
<table cellpadding="0" cellspacing="0" align="center"  width="100%">
		<tr>
			<td class="subtitle" colspan="4"  height="30">Informaci&oacute;n General</td>
		</tr>
</table>															
						
<table cellpadding="0" cellspacing="0" align="center"  width="50%">
  <tr>
	<td class="subtitle" colspan="2"  height="30">Datos Personales</td>
  </tr>
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="prompt.name" /> :</td>
    <td>${customer.fullName} &nbsp;</td>
  </tr>
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="label.id" bundle="messages" /> : :</td>
    <td>${customer.idType.name} - <strong>${customer.identity} &nbsp;</td>
  </tr>
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="label.phone" bundle="messages" /> Domicilio :</td>
    <td>${customer.billInfo.phone} &nbsp;</td>
  </tr>
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="label.phone" bundle="messages" /> Oficina :</td>
    <td>${customer.officePhone} &nbsp;</td>
  </tr>  
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="label.email" bundle="messages" /> :</td>
    <td>${customer.email} &nbsp;</td>
  </tr>
  <tr>
	<td class="subtitle" colspan="2"  height="30">Datos Icaro Online</td>
  </tr>
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="prompt.username" /> :</td>
    <td>${customer.userName} &nbsp;</td>
  </tr>
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="label.creation" bundle="messages" /> :</td>
    <td><fmt:formatDate value="${customer.creation.time}" pattern="${initParameters.DATE_TIME_FORMAT}" /> &nbsp;</td>
  </tr>
  <tr>
	<td class="subtitle" colspan="2"  height="30">Direccion</td>
  </tr>
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="label.country" bundle="messages" /> :</td>
    <td>${customer.fullName} &nbsp;</td>
  </tr>
 
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"> <bean:message key="prompt.state" /> :</td>
    <td>${customer.homeAddress.stateName}&nbsp;</td>
  </tr>
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="label.city" bundle="messages" /> :</td>
    <td>${customer.homeAddress.cityName} &nbsp;</td>
  </tr>
  <tr>
    <td class="label" style="text-align:left; padding-left:20px;"><bean:message key="label.address" bundle="messages" /> :</td>
    <td>${customer.address} &nbsp;</td>
  </tr>
</table>					
					
						</td>			            
						</tr>						
						</table>    
					
						                       										
					</td>
				</tr>
				<tr>
					<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
				</tr>
			</table>	
		</td>
		</tr>
		</table>
		</html:form>
		<html:javascript formName="customerForm" dynamicJavascript="true" staticJavascript="false" />
		<script language="JavaScript1.2">
			function preValidate (frm) { 
		    	if (bCancel) { 
		      		return true; 
				}
			  <c:choose>
			  	<c:when test="${!empty customerForm.code && customerFormorm.code != 0}">
					var msg = 'Se actualizar\u00E1 el ${systemMenuLabel}. ¿Desea continuar?';
				</c:when>
				<c:otherwise>
					var msg = 'Est\u00E1 a punto de crear un nuevo ${systemMenuLabel}.  ¿Desea continuar?';
				</c:otherwise>
			  </c:choose>
			  if( confirm (msg)){
				  return validateCustomerForm(frm);
			  } else { return false; }
			}
		</script>
	</td>
</tr>
<tr>
	<td background="${pageContext.request.contextPath}/images/tables/file_bottom.gif"><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="7" border="0" alt=""></td>
</tr>
<tr>
	<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0" alt=""></td>
</tr>
</table>

