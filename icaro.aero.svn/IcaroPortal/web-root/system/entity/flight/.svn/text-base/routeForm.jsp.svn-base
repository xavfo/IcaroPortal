<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxtags/Ajax.js"></script>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">
			<c:set var="systemMenuLabel" ><bean:message key="icaro.flight.route" bundle="systemMenu" /></c:set>
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
				<c:when test="${!empty routeForm.code && routeForm.code != 0}">
					<bean:message key="message.edit" bundle="systemHelp" arg0="${routeForm.name}" />
				</c:when>
				<c:otherwise>
					<bean:message key="message.create" bundle="systemHelp" />
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

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

		<html:form action="/system/portal/route/save" focus="name" onsubmit="return preValidate(this)">
		<html:hidden property="action" value="save" />
		<html:hidden property="code" />
		<html:hidden property="tab" value="${param.tab}" />
		<table class="tabForm" border="0" cellpadding="0" cellspacing="4" width="100%">		
		<tr>
			<td><img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0"></td>
		</tr>
		<tr>
			<td>		
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
			<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td class="subtitle">
						<c:choose>
							<c:when test="${!empty routeForm.code && routeForm.code != 0}">
								<bean:message key="message.edit" bundle="systemHelp" arg0="${routeForm.name}" />
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
					<td><html:text property="name" styleClass="input" style="width: 200px;" maxlength="200" /></td>
				</tr>
				<tr>
					<td class="required">* <bean:message key="prompt.enabled" /> :</td>
					<td>
						<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
						<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
					</td>										
				</tr>	
	
				<tr>
					<td class="optional" nowrap> <bean:message key="prompt.notes" /> :</td>
					<td>
						<html:textarea property="notes" styleId="input" style="width: 630px; height: 80px;" />	
					</td>
				</tr>
								<tr>
					<td class="optional"><bean:message key="prompt.routeRateDoc" /> :</td>
					<td><html:text property="rateDoc" styleClass="input" style="width: 200px;" maxlength="200" />
					
					<a href="javascript: openUploadWindow('/popup/openUploadFile.do', '/files/rates', 'routeForm', 'rateDoc','rateDoc','${initParameters.SEQ_ROUTEDOC}',0)";"  onMouseOver="MM_swapImage('uploadImg','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="uploadImg" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />">
			</a>
				<a href="${pageContext.request.contextPath}${routeForm.rateDoc}"><img name="previewImg" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />" >
			</a>
			<a href="javascript: clearControls(0, 'rateDoc');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
				<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
			</a>
					
					</td>
				</tr>
				
				<tr>
					<td class="optional" nowrap> <bean:message key="prompt.routeRateNotes" /> :</td>
					<td>
						<html:textarea property="rateNotes" styleId="input" style="width: 630px; height: 80px;" />	
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
			</td>
		</tr>
		</table>
		
		</html:form>
		<html:javascript formName="routeForm" dynamicJavascript="true" staticJavascript="false" />
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
	  	<c:when test="${!empty routeForm.code && routeForm.code != 0}">
			var msg = 'Se actualizar\u00E1 el item de ${systemMenuLabel}. ¿Desea continuar?';
		</c:when>
		<c:otherwise>
			var msg = 'Est\u00E1 a punto de crear un nuevo item de ${systemMenuLabel}.  ¿Desea continuar?';
		</c:otherwise>
	  </c:choose>
	  if( confirm (msg)){
		  return validateRouteForm(frm);
	  } else { return false; }
	}
	
	

</script>
