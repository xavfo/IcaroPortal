<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/struts-html" prefix="html" %>
<%@ taglib uri="/struts-bean" prefix="bean" %>

<!-- Head -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td class="title" width="300">${currentItem.name}<br/> - <span class="subtitle"><bean:message key="bulletin.bulletin" bundle="systemMenu" /></span></td>
		<td align="right">
			<bean:message key="service" bundle="systemMenu" /> >
			<bean:message key="bulletin.topic" bundle="systemMenu" /> >
			<bean:message key="bulletin.bulletin" bundle="systemMenu" />
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
<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="25" border="0"><br>

<table border="0" cellpadding="0" cellspacing="0" align="center" width="100%">
	<tr>
		<td >
		    <!-- Container TABS -->
			<tiles:insert definition="tabs.bulletin">
				<tiles:put name="code" value="${currentItem.code}" />
			</tiles:insert>
			<!-- END Container TABS -->
		</td>
	</tr>
	<tr>
		<td>
		
			<table class="tabForm" border="0" cellpadding="4" cellspacing="0" width="100%">
				<tr>
					<td>
							
							<html:form action="/system/bulletin/bulletin/save" focus="templateCode" onsubmit="return validateBulletinForm(this);">
							<html:hidden property="action" value="save" />
							<html:hidden property="code" />
							<html:hidden property="itemCode" value="${currentItem.code}" />
							<html:hidden property="templateCode" />
							<html:hidden property="topicCode" value="${currentItem.code}"/>
							<html:hidden property="tab" value="${param.tab}" />
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tr>
									<td class="subtitle">
										<c:choose>
											<c:when test="${!empty bulletinForm.code && bulletinForm.code != 0}">
												<bean:message key="message.edit" bundle="systemHelp" arg0="${bulletinForm.subject}" />
											</c:when>
											<c:otherwise>
												<bean:message key="message.create" bundle="systemHelp" />
											</c:otherwise>
										</c:choose>
									</td>
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
							<img src="${pageContext.request.contextPath}/images/spacer.gif" width="1" height="5" border="0"><br>

							<table align="right" border="0" cellpadding="4" cellspacing="0" width="95%">
								<tr>
									<td height="30" width="170"></td>
									<td><bean:message key="message.required" bundle="systemHelp" /></td>
								</tr>
								<tr>
									<td class="required">* <bean:message key="prompt.subject" /> :</td>
									<td><html:text property="subject" styleClass="input" style="width: 350px;" /></td>
								</tr>
								<tr>
									<td class="required">* <bean:message key="prompt.title" /> :</td>
									<td><html:text property="title" styleClass="input" /></td>
								</tr>
								<tr>
									<td class="optional"><bean:message key="prompt.image" /> :</td>
									<td>
										<html:text property="image" styleClass="input" style="width: 250px;" />
										<a href="javascript: openUploadWindow('/popup/openUploadFile.do', '/images/bulletin/images', 'bulletinForm', 'image','image-','${initParameters.FILE_IMAGE_SEQUENCE_NAME}',0)" onMouseOver="MM_swapImage('uploadImg','','${pageContext.request.contextPath}/images/icons/upload_roll.gif',1)" onMouseOut="MM_swapImgRestore()"><img name="uploadImg" src="${pageContext.request.contextPath}/images/icons/upload.gif" width="20" height="20" border="0" alt="<bean:message key="button.upload" bundle="messages" />"></a>
										<a href="javascript: openDocumentPreview(document.forms[0].image.value);" onMouseOver="MM_swapImage('previewImg2','','${pageContext.request.contextPath}/images/icons/preview_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
											<img name="previewImg2" src="${pageContext.request.contextPath}/images/icons/preview.gif" width="20" height="20" border="0" alt="<bean:message key="button.preview" bundle="messages" />">
										</a>
										<a href="javascript: clearControls(0, 'image');" onMouseOver="MM_swapImage('cleanImg','','${pageContext.request.contextPath}/images/icons/clean_roll.gif',1)" onMouseOut="MM_swapImgRestore()">
											<img name="cleanImg" src="${pageContext.request.contextPath}/images/icons/clean.gif" width="20" height="20" border="0" alt="">
										</a>
										
										
										<%-- input type="button" name="btnDate" value="..." style="width: 20px" onClick="openUploadWindow('/popup/openUpload.do', '/images/bulletin/images', 'bulletinForm', 'image','bulletinForm.image',0)" --%>
									</td>
								</tr>
								<tr>
									<td class="required">* <bean:message key="prompt.content" /> :</td>
									<td>
										<html:hidden property="content" styleId="content" />
										<input type="hidden" id="content___Config" value="SkinPath=${pageContext.request.contextPath}/js/fckeditor/editor/skins/silver/&DefaultLanguage=en&AutoDetectLanguage=true">
										<iframe name="content___Frame" id="content___Frame" src="${pageContext.request.contextPath}/js/fckeditor/editor/fckeditor.html?InstanceName=content" width="100%" height="200" frameborder="no" scrolling="no"></iframe>
									</td>
								</tr>
							</table>
							</html:form>
							<html:javascript formName="bulletinForm" dynamicJavascript="true" staticJavascript="false" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>