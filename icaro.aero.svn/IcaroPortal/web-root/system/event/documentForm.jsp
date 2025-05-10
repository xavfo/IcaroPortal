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
			<c:choose>
				<c:when test="${documentContainerForm.docTypeCode eq initParameters.DOCUMENT_TYPE_DOCUMENTS}">
					<bean:message key="document.document" bundle="systemMenu" />
					<c:set var="systemMenuLabel" ><bean:message key="document.document" bundle="systemMenu" /></c:set>
				</c:when>
				<c:otherwise>
					<bean:message key="information.container.support" bundle="systemMenu" />
					<c:set var="systemMenuLabel" ><bean:message key="information.container.support" bundle="systemMenu" /></c:set>
				</c:otherwise>
			</c:choose>		
		</td>
		<td align="right">
			<bean:message key="information" bundle="systemMenu" /> >
			<bean:message key="container.container" bundle="systemMenu" /> >
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
			<ul>
			<div class="error"><html:errors/></div>		
	</td>
	</tr>
</table>
<img src="${pageContext.request.contextPath}/images/shim.gif" width="1" height="20" border="0">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td>
		<tiles:insert definition="tabs.system.events">
			<tiles:put name="code" value="${eventForm.code}" />
			<tiles:put name="tab" value="3" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>
		<html:form action="/system/event/document/save"  onsubmit="return preValidate(this)">
		<html:hidden property="action" value="save" />
		<html:hidden property="code" />
		<html:hidden property="docTypeCode"  value="${initParameters.DOCUMENT_TYPE_DOCUMENTS}" />
		<html:hidden property="displayModeCode" value="${initParameters.DISPLAY_MODE_LINK}"  />
		<html:hidden property="ownerCode" value="${eventForm.code}"/>
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
						<table cellpadding="0" cellspacing="0" align="center"  width="100%">
							<tr>
								<td>
								  <table cellpadding="0" cellspacing="4" border="0">
								  	<tr>
										<td class="required">*<bean:message key="prompt.title" /> :</td>
										<td><html:text property="title" styleClass="input" style="width: 200px;" maxlength="200" /></td>
									</tr>			
									<tr>
										<td class="required">*<bean:message key="prompt.description" /> :</td>
										<td>
											<html:textarea property="description" cols="40" rows="3" />											
										</td>
									</tr>	
									<tr>
										<td class="required">*<bean:message key="prompt.category" /> :</td>
										<td>
											<html:select property="categoryCode" size="1" styleClass="input" style="width: 250px;">
												<html:options collection="categoryList" property="code" labelProperty="name"/>
											</html:select>
										</td>
									</tr>
								
									<tr>
										<td class="optional"><bean:message key="prompt.keywords" /> :</td>
										<td>
											<html:textarea property="keywords" styleId="leadinText" cols="40" rows="3" />	
										</td>
									</tr>		
									<tr>
										<td class="required">*<bean:message key="prompt.document" />:</td>
										<td>
											<html:text property="path" styleClass="input" style="width: 300px;" maxlength="255" readonly="true" />
											<a href="javascript: openUploadWindow('/popup/openUploadFile.do', '/documentos/compartido', 'documentContainerForm', 'path','gen-','${initParameters.SEQ_DOCUMENT}',0)" onMouseOver="MM_swapImage('uploadImg','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="uploadImg" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />">
											</a>	
											<a href="javascript: openDocumentPreview(document.forms[0].path.value)"  onMouseOver="MM_swapImage('previewImg2','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="previewImg2" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
											</a>
											<a href="javascript: clearControls(0, 'path');" onMouseOver="MM_swapImage('cleanImg2','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
												<img name="cleanImg2" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
											</a>
										</td>
									</tr>	
									<tr>
										<td class="required">*<bean:message key="prompt.enabled" /></td>
										<td>
											<html:radio property="isEnabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
											<html:radio property="isEnabled" value="false" /> <bean:message key="label.no" bundle="messages" />
										</td>		
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
		<html:javascript formName="documentContainerForm" dynamicJavascript="true" staticJavascript="false" />
		<script language="JavaScript1.2">
			function preValidate (frm) { 
		    	if (bCancel) { 
		      		return true; 
				}
			  <c:choose>
			  	<c:when test="${!empty documentContainerForm.code && documentContainerForm.code != 0}">
					var msg = 'Se actualizar\u00E1 el documento. ¿Desea continuar?';
				</c:when>
				<c:otherwise>
					var msg = 'Est\u00E1 a punto de crear un nuevo documento.  ¿Desea continuar?';
				</c:otherwise>
			  </c:choose>
			  if( confirm (msg)){
				  return validateDocumentContainerForm(frm);
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


