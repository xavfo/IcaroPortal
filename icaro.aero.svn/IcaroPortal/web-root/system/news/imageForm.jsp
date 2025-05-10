<%@ page language="java" %>
<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="350">		
			<bean:message key="news.news" bundle="systemMenu" /> -
			<c:set var="systemMenuLabel" ><bean:message key="information.related.images" bundle="systemMenu" /></c:set>
			${systemMenuLabel}
		</td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="news.news" bundle="systemMenu" /> >
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
		<tiles:insert definition="tabs.system.news">
			<tiles:put name="code" value="${newsForm.code}" />
			<tiles:put name="tab" value="3" />
		</tiles:insert>
	</td>
</tr>
<tr>
	<td>
		<html:form action="/system/news/image/save"  onsubmit="return preValidate(this)">
		<html:hidden property="action" value="save" />
		<html:hidden property="code" />
		<html:hidden property="ownerCode" value="${newsForm.code}"/>
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
										<td class="required">*<bean:message key="prompt.leadinImage" /> :</td>
										<td>
											<html:hidden property="thumbnailImageCode" style="width: 250px;" />
											<html:text property="thumbnailImageName" style="width: 250px;" readonly="true" />
											<a href="javascript: openUploadMediaWindow('/popup/openUploadMediaFile.do', '/news', 'imageContainerForm', 0, ${initParameters.MEDIA_TYPE_IMAGE},
																			  'thumbnailImageCode', 'thumbnailImageName');" >
												<img src="${pageContext.request.contextPath}/images/system/icons/upload.gif" border="0">
											</a>
											<a href="javascript: openPreviewMediaWindow('thumbnailImageCode', 0, ${initParameters.MEDIA_TYPE_IMAGE})" >
												<img src="${pageContext.request.contextPath}/images/system/icons/preview.gif" border="0">
											</a>
											<a href="javascript: clearControls(0, 'thumbnailImageCode|thumbnailImageName');" >
												<img src="${pageContext.request.contextPath}/images/system/icons/clean.gif" border="0">
											</a>
										</td>		
									</tr>
									<tr>
										<td class="required">*<bean:message key="prompt.image" /> :</td>
										<td>
										    <html:hidden property="largeImageCode" style="width: 250px;" />
											<html:text property="largeImageName" style="width: 250px;" readonly="true" />
											<a href="javascript: openUploadMediaWindow('/popup/openUploadMediaFile.do', '/news', 'imageContainerForm', 0, ${initParameters.MEDIA_TYPE_IMAGE},
																			  'largeImageCode', 'largeImageName');" >
												<img src="${pageContext.request.contextPath}/images/system/icons/upload.gif" border="0">
											</a>
											<a href="javascript: openPreviewMediaWindow('largeImageCode', 0, ${initParameters.MEDIA_TYPE_IMAGE})" >
												<img src="${pageContext.request.contextPath}/images/system/icons/preview.gif" border="0">
											</a>
											<a href="javascript: clearControls(0, 'largeImageCode|largeImageName');" >
												<img src="${pageContext.request.contextPath}/images/system/icons/clean.gif" border="0">
											</a>
										</td>
									</tr>
									<tr>
										<td class="optional"><bean:message key="prompt.description" /> :</td>
										<td>
											<html:textarea property="description" cols="40" rows="3" />											
										</td>
									</tr>	
									<tr>
										<td class="optional"><bean:message key="prompt.publishPeriod" /> :</td>
										<td>
											<table>
												<tr>
													<td class="optional"><bean:message key="prompt.from" /> :</td>
													<td><html:text property="fromStr" styleId="fromDate" style="width: 100px;" /> <input type="button" name="fromBtn" id="fromBtn" value="..." style="width: 20px;"></td>
													<td class="optional"><bean:message key="prompt.to" /> :</td>
													<td><html:text property="toStr" styleId="toDate" style="width: 100px;" /> <input type="button" name="toBtn" id="toBtn" value="..." style="width: 20px;"></td>
												</tr>	
											</table>
										</td>
									</tr>
									<tr>
										<td class="required">*<bean:message key="prompt.enabled" /> : </td>
										<td>
											<html:radio property="enabled" value="true" /> <bean:message key="label.yes" bundle="messages" />
											<html:radio property="enabled" value="false" /> <bean:message key="label.no" bundle="messages" />
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
		<html:javascript formName="imageContainerForm" dynamicJavascript="true" staticJavascript="false" />
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
				  return validateImageContainerForm(frm);
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
<script language="JavaScript1.2">
	Calendar.setup(
		{
			inputField  	: "fromDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "fromBtn"
		}
	);
	Calendar.setup(
		{
			inputField  	: "toDate",
			ifFormat    	: "%Y-%m-%d",
			dateFormat		: "%Y-%m-%d",
			weekNumbers		: false,
			align			: "Bl",
			button      	: "toBtn"
		}
	);
</script>