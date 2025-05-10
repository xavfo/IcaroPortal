<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<bean:message key="cart.tax" bundle="systemMenu" />
			<c:set var="systemMenuLabel" ><bean:message key="cart.tax" bundle="systemMenu" /></c:set>
		</td>
		<td align="right">
			<bean:message key="cart" bundle="systemMenu" /> &gt;
			${systemMenuLabel}
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>

<html:form action="/system/cart/tax/save" focus="name" onsubmit="return preValidate(this)">
<html:hidden property="action" value="save" />
<html:hidden property="tab" value ="${param.tab}" />
<html:hidden property="code" />
<table border="0" cellpadding="4" cellspacing="0" width="100%">
<tr>
  <td align="right">
  	<tiles:insert definition="system.navBar">
		<tiles:put name="save" 	value="true" />
		<!-- tiles:put name="apply"		value="true" /-->
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
				<c:when test="${!empty taxForm.code && taxForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${taxForm.code}" />
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
  <%--tr>
  	<td class="label"><bean:message key="prompt.code" /> :</td>
  	<td><html:text property="taxCode" styleClass="box" styleId="taxCode" style="width: 120px;" readonly="true"/></td>
  </tr --%>
  <%--c:choose>
    <c:when test="${!empty taxForm.code && taxForm.code != 0}">
      <tr>
        <td class="label"><bean:message key="prompt.taxName" /> :</td>
        <td><html:text property="taxName" styleClass="box" styleId="name" style="width: 120px;" readonly="true"/></td>
      </tr>
    </c:when>
    <c:otherwise>
      <tr>
        <td class="required">* <bean:message key="prompt.taxName" /> :</td>
        <td><html:text property="taxName" styleClass="input" style="width: 120px;" /></td>
      </tr>
    </c:otherwise>
  </c:choose--%>
  <tr>
    <td class="required">* <bean:message key="prompt.taxName" /> :</td>
    <td><html:text property="taxName" styleClass="input" style="width: 120px;" /></td>
  </tr>
  <tr>
    <td class="required">* <bean:message key="prompt.taxRate" /> :</td>
    <td><html:text property="rateStr" styleClass="input" style="width: 120px;" /></td>
  </tr>
  <tr>
    <td class="required">* <bean:message key="prompt.taxSince" /> :</td>
    <td><html:text property="sinceStr" styleClass="input" styleId="sinceStr" style="width: 120px;" readonly="true"/> <input type="button" name="sinceBtn" id="sinceBtn" value="..." style="width: 20px;"/></td>
  </tr>
  <%--tr>
    <td class="label"><bean:message key="prompt.taxUntil" /> :</td>
    <td><html:text property="untilStr" styleClass="box" styleId="untilStr" style="width: 120px;" readonly="true"/></td>
  </tr--%>
  <tr>
  	<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
  </tr>
</table>
<html:hidden property="untilStr" />
</html:form>
<html:javascript formName="taxForm" dynamicJavascript="true" staticJavascript="false" />

<script language="JavaScript1.2">
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	  <c:choose>
	  	<c:when test="${!empty taxForm.code && taxForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateTaxForm(frm);
	  } else { return false; }
	}
</script>
<script language="JavaScript1.2">
  Calendar.setup(
    {
      inputField    : "sinceStr",
      ifFormat      : "%Y-%m-%d",
      dateFormat    : "%Y-%m-%d",
      weekNumbers   : false,
      align         : "Bl",
      button        : "sinceBtn"
    }
  );
  
</script>
