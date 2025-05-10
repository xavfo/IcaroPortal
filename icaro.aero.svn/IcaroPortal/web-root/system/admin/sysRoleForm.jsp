<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>

<%--
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="administration.role" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="administration" bundle="systemMenu" /> >
			<bean:message key="administration.role" bundle="systemMenu" />
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
		<td align="right">
			<c:url value="/system/admin/sysRole.do" var="urlBack">
				<c:param name="action" value="list" />
			</c:url>
			<a href="${urlBack}" class="back"><bean:message key="label.listAll" bundle="messages" /> <bean:message key="administration.role" bundle="systemMenu" /></a>
		</td>			
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty sysRoleForm.code && sysRoleForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${sysRoleeForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

<html:form action="/system/admin/sysRole/save" focus="name" onsubmit="return validateSysRoleForm(this);">
<html:hidden property="action" value="save" />
<html:hidden property="code" />
<html:hidden property="parentCode" value="1" />
<table class="form" align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
	<tr>
		<td height="30" width="120"></td>
		<td><bean:message key="message.required" bundle="systemHelp" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.name" /> :</td>
		<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
	</tr>
	<tr>
		<td class="required">* <bean:message key="prompt.description" /> :</td>
		<td><html:textarea property="description" styleClass="input" style="height: 60px; width: 200px;" /></td>
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
<html:javascript formName="sysRoleForm" dynamicJavascript="true" staticJavascript="false" />
--%>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300"><bean:message key="administration.role" bundle="systemMenu" /></td>
		<td align="right">
			<bean:message key="administration" bundle="systemMenu" /> >
			<bean:message key="administration.role" bundle="systemMenu" />
			<c:set var="systemMenuLabel"><bean:message key="administration.role" bundle="systemMenu" /></c:set>
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
			<ul>
			<div class="error"><html:errors/></div>		
	</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="subtitle">
			<c:choose>
				<c:when test="${!empty sysRoleForm.code && sysRoleForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${sysRoleForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
	<html:form action="/system/admin/sysRole/save" focus="name" onsubmit="return validateSysRoleForm(this);">
	<html:hidden property="action" value="save" />
	<html:hidden property="code" />
	<html:hidden property="parentCode" value="1" />
		<table border="0" cellpadding="0" cellspacing="4" width="100%">		
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
						<!--inicio seccion general information-->
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
						<table class="form" cellpadding="0" cellspacing="0" align="center"  width="100%">
							<tr>
								<td>
								  <table  cellpadding="0" cellspacing="4" border="0">
									<tr>
										<td class="required">* <bean:message key="prompt.name" /> :</td>
										<td><html:text property="name" styleClass="input" style="width: 200px;" /></td>
									</tr>
									<tr>
										<td class="optional"> <bean:message key="prompt.description" /> :</td>
										<td><html:textarea property="description" styleClass="input" style="height: 60px; width: 200px;" /></td>
									</tr>
									<tr>
										<td height="30" width="170"></td>
										<td><bean:message key="message.required" bundle="systemHelp" /></td>
									</tr>
								  </table>															
								</td>
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
		<html:javascript formName="sysRoleForm" dynamicJavascript="true" staticJavascript="false" />
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
	  	<c:when test="${!empty sysRolForm.code && sysRolForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateSysRolForm(frm);
	  } else { return false; }
	}
</script>

