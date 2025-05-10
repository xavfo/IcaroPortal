<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<script language="JavaScript" type="text/javascript">
<!--
	function checkSellerOtion(element) {
		//alert(element.options[element.selectedIndex].value);
		var row = document.getElementById("sellerOption");
		if (element.options[element.selectedIndex].value == ${initParameters.SYS_ROLE_SELLER} ) {
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
			<bean:message key="administration.user" bundle="systemMenu" /></td>
			<c:set var="systemMenuLabel" ><bean:message key="administration.user" bundle="systemMenu" /></c:set>
		<td align="right">
			<bean:message key="administration" bundle="systemMenu" /> >
			${systemMenuLabel}
		</td>
	</tr>
</table>
<hr width="100%" size="1" color="#999999" noshade>

<html:form action="/system/admin/sysUser/save" focus="name" onsubmit="return preValidate(this);">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="reset" />


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
				<c:when test="${!empty sysUserForm.code && sysUserForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${sysUserForm.name}" />
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
		<td class="required">* <bean:message key="prompt.role" /> :</td>
		<td>
			<html:select property="roleCode" size="1" styleClass="input" style="width: 250px;" onchange="checkSellerOtion(this)">
				<html:option value="" ><bean:message key="option.select" bundle="messages" /></html:option>
				<c:forEach var="item" items="${sysRoleList}" varStatus="status">
					<c:if test="${ item.code != 1 }">					
						<html:option value="${item.code}">${item.name}</html:option>					
					</c:if>	
				</c:forEach>
			</html:select>		
		</td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.username" /> :</td>
		<td><html:text property="name" styleClass="input" style="width:100px;" /></td>
	</tr>	
	<c:if test="${empty sysUserForm.code || sysUserForm.code == 0}">
	<tr>
		<td class="required">* <bean:message key="prompt.password" /> :</td>
		<td><html:password property="password" styleClass="input" style="width: 100px;" value="" maxlength="50"/></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.passwordVerify" /> :</td>
		<td><html:password property="confirmPassword" styleClass="input" style="width: 100px;" value="" maxlength="50"/></td>
	</tr>	
	</c:if>
	<tr>
		<td class="required">* <bean:message key="prompt.email" /> :</td>
		<td><html:text property="email" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.firstName" /> :</td>
		<td><html:text property="firstName" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="optional"><bean:message key="prompt.lastName" /> :</td>
		<td><html:text property="lastName" styleClass="input" style="width: 200px;" /></td>
	</tr>	
	<tr>
		<td class="required">* <bean:message key="prompt.enabled" /> :</td>
		<td>
			<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
			<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />		
		</td>
	</tr>
	<c:choose>
		<c:when test="${sysUser.accessMode.code == 1}">
			<tr>
			  <td class="optional">mode de acceso: </td>
			  <td>
				<html:select property="accessModeCode" size="1" styleClass="input" style="width: 250px;">
					<html:options collection="sysAccessModeList" property="code" labelProperty="name"/>
				</html:select>
			  </td>
			</tr>
		</c:when>
		<c:otherwise>
			<html:hidden property="accessModeCode" value="2" />
		</c:otherwise>
	</c:choose>
	<tr>
		<td height="30" width="170"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td colspan="2"><img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br></td>
	</tr>
</table>
</html:form>
<html:javascript formName="sysUserForm" dynamicJavascript="true" staticJavascript="false" />

<script language="JavaScript1.2">
	function preValidate (frm) { 
    	if (bCancel) { 
      		return true; 
		}
	 var msg = 'Est\u00E1 seguro que desea guardar las modificaciones realizadas?';
	  if( confirm (msg)){
		  return validateSysUserForm(frm);
	  } else { return false; }
	}
</script>
